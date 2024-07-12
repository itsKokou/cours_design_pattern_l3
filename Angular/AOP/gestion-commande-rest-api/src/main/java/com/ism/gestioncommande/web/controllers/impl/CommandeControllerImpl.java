package com.ism.gestioncommande.web.controllers.impl;

import com.ism.gestioncommande.data.entities.Article;
import com.ism.gestioncommande.data.entities.Client;
import com.ism.gestioncommande.data.entities.Commande;
import com.ism.gestioncommande.data.repositories.ClientRepository;
import com.ism.gestioncommande.data.repositories.CommandeRepository;
import com.ism.gestioncommande.services.ArticleService;
import com.ism.gestioncommande.services.ClientService;
import com.ism.gestioncommande.services.CommandeService;
import com.ism.gestioncommande.web.controllers.CommandeController;
import com.ism.gestioncommande.web.dto.request.ArticlePanierDto;
import com.ism.gestioncommande.web.dto.request.ClientCreateRequestDto;
import com.ism.gestioncommande.web.dto.request.PanierDto;
import com.ism.gestioncommande.web.dto.response.ArticleSimpleResponseDto;
import com.ism.gestioncommande.web.dto.response.ClientResponseDto;
import com.ism.gestioncommande.web.dto.response.CommandeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.text.DateFormat;

@Controller
@RequiredArgsConstructor
public class CommandeControllerImpl implements CommandeController {

    private final CommandeService commandeService;
    private final ClientService clientService;
    private final ArticleService articleService;
    @Override
    public String listerClientCommande(Model model,
                                       @PathVariable Long id,
                                       @RequestParam(required = false) String date,
                                       @RequestParam(defaultValue = "0")int page,
                                       @RequestParam(defaultValue = "6")int size) throws ParseException {
        Page<Commande> commandes;
        if (date != null && !date.equals("")){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date dateVrai = formatter.parse(date);
            commandes = commandeService.getByClientIdAndDate(id,dateVrai,PageRequest.of(page,size));
        }else{
            commandes = commandeService.getByClientId(id,PageRequest.of(page,size));
        }
        Page<CommandeResponseDto> commandesDtos = commandes.map(CommandeResponseDto::toDto);
        model.addAttribute("commandes",commandesDtos.getContent());
        model.addAttribute("client",clientService.getClientById(id));
        model.addAttribute("pages",new int[commandes.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("date",date);
        model.addAttribute("nbrePage",commandes.getTotalPages());
        return "commande/commande";
    }

    @Override
    public String listerAllCommande(Model model, String date, int page, int size) throws ParseException {
        Page<Commande> commandes;
        if (date != null && !date.equals("")){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date dateVrai = formatter.parse(date);
            commandes = commandeService.getByDate(dateVrai,PageRequest.of(page,size));
        }else{
            commandes = commandeService.getAll(PageRequest.of(page,size));
        }
        Page<CommandeResponseDto> commandesDtos = commandes.map(CommandeResponseDto::toDto);
        model.addAttribute("commandes",commandesDtos.getContent());
        model.addAttribute("pages",new int[commandesDtos.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("date",date);
        model.addAttribute("nbrePage",commandesDtos.getTotalPages());
        return "commande/commande";
    }

    @Override
    public String showForm(Model model,  @PathVariable Long id, @ModelAttribute("panier") PanierDto panier) {
        Client client = clientService.getClientById(id);
        if(client == null){
            return "redirect:/client";
        }
        panier.setClient(ClientResponseDto.toDto(client));
        List<Article> articlesForCommande = articleService.getArticlesForCommande();
        List<ArticleSimpleResponseDto> articlesDtos = articlesForCommande.stream().map(article -> new ArticleSimpleResponseDto(article.getId(), article.getLibelle())).toList();
        model.addAttribute("articlesSelectForm",articlesDtos);
        model.addAttribute("panier",panier);
        model.addAttribute("articleForm", new ArticlePanierDto());
        return "commande/form.add.commande";
    }

    @Override
    public String saveCommande(Model model, @ModelAttribute("panier") PanierDto panier) {
        commandeService.saveCommande(panier);
        Long idClient = panier.getClient().getId();
        model.addAttribute("panier", InitializePanier());
        panier.getArticlesPaniers().clear();
        panier.setTotal(0.0);
        panier.setClient(null);
        return "redirect:/commandes/client/"+idClient;
    }

    @ModelAttribute("panier")
    public PanierDto InitializePanier(){
        return new PanierDto(
                new ArrayList<>(),
                0.0,
                null
        );
    }
}
