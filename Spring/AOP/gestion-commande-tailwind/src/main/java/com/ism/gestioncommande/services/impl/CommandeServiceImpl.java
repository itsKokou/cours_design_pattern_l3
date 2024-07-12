package com.ism.gestioncommande.services.impl;

import com.ism.gestioncommande.data.entities.Article;
import com.ism.gestioncommande.data.entities.Client;
import com.ism.gestioncommande.data.entities.Commande;
import com.ism.gestioncommande.data.entities.LigneCommande;
import com.ism.gestioncommande.data.enums.EtatCommande;
import com.ism.gestioncommande.data.repositories.ArticleRepository;
import com.ism.gestioncommande.data.repositories.ClientRepository;
import com.ism.gestioncommande.data.repositories.CommandeRepository;
import com.ism.gestioncommande.data.repositories.LigneCommandeRepository;
import com.ism.gestioncommande.exceptions.EntityNotFoundException;
import com.ism.gestioncommande.services.CommandeService;
import com.ism.gestioncommande.web.dto.request.PanierDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Transactional //Si un produit ne s'insère pas, tout le commit ne passe pas
public class CommandeServiceImpl implements CommandeService {
    private final CommandeRepository commandeRepository;
    private final ClientRepository clientRepository;
    private final ArticleRepository articleRepository;
    private final LigneCommandeRepository ligneCommandeRepository;
    @Override
    public Page<Commande> getByClientId(Long id, Pageable pageable) {
        return commandeRepository.findByClientIdAndActiveTrue(id,pageable);
    }

    @Override
    public Page<Commande> getByClientIdAndDate(Long id, Date date, Pageable pageable) {
        return commandeRepository.findByClientIdAndDateAndActiveTrue(id,date,pageable);
    }

    @Override
    public Page<Commande> getAll(Pageable pageable) {
        return commandeRepository.findAllByActiveTrue(pageable);
    }

    @Override
    public Page<Commande> getByDate(Date date, Pageable pageable) {
        return commandeRepository.findAllByActiveTrueAndDate(date,pageable);
    }


    @Override
    public void saveCommande(PanierDto panierDto) {
        Client client = clientRepository.findById(panierDto.getClient().getId())
                .orElseThrow(()->new EntityNotFoundException("Ce client n'existe pas "));
        Commande commande = new Commande(
                new Date(),
                panierDto.getTotal(),
                EtatCommande.Encours,
                panierDto.getClient().getAdresse(),
                null,
                client
        );
        commande.setActive(true);
        commandeRepository.save(commande);
        List<LigneCommande> ligneCommandes =  panierDto.getArticlesPaniers().stream().map(
                articlePanierDto -> {
                    Article article = articleRepository.findById(articlePanierDto.getId())
                            .orElseThrow(()->new EntityNotFoundException("L'article n'existe pas"));
                    LigneCommande lc =  new LigneCommande(
                            articlePanierDto.getPrix(),
                            articlePanierDto.getQuantite(),
                            articlePanierDto.getMontant(),
                            article,
                            commande
                    );
                    lc.setActive(true);
                    return lc;
                }
        ).toList();
        ligneCommandeRepository.saveAllAndFlush(ligneCommandes);

    }

    @Override
    public void save(Commande data) {

    }

    @Override
    public List<Commande> getAll() {
        return null;
    }

    @Override
    public Optional<Commande> show(Long dataID) {
        return Optional.empty();
    }
}
