package com.ism.gestioncommande.data.fixtures;

import com.ism.gestioncommande.data.entities.Article;
import com.ism.gestioncommande.data.entities.Commande;
import com.ism.gestioncommande.data.entities.LigneCommande;
import com.ism.gestioncommande.data.repositories.ArticleRepository;
import com.ism.gestioncommande.data.repositories.CommandeRepository;
import com.ism.gestioncommande.data.repositories.LigneCommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//@Component
//@Order(value = 3)
@RequiredArgsConstructor
public class LigneCommandeFixtures implements CommandLineRunner {
    private final LigneCommandeRepository ligneCommandeRepository;
    private final CommandeRepository commandeRepository;
    private final ArticleRepository articleRepository;
    
    @Override
    public void run(String... args) throws Exception {
        List<Commande> commandes = commandeRepository.findAll();
        List<Article> articles = articleRepository.findAll();
        for (int i = 1; i < 31; i++) {
            Article article = articles.get(ThreadLocalRandom.current().nextInt(0,  articles.size()));
            Commande commande = commandes.get(ThreadLocalRandom.current().nextInt(0,  commandes.size()));
            LigneCommande ligneCommande = new LigneCommande();
            ligneCommande.setActive(true);
            ligneCommande.setPrix(article.getNouveauPrix());
            if (i % 2 == 0) {
                ligneCommande.setQte(2);
            }else{
                ligneCommande.setQte(3);
            }
            ligneCommande.setMontant(ligneCommande.getQte()*ligneCommande.getPrix());
            ligneCommande.setArticle(article);
            ligneCommande.setCommande(commande);
            commande.setMontant(commande.getMontant()+ligneCommande.getMontant());

            ligneCommandeRepository.save(ligneCommande);
            commandeRepository.save(commande);
        }
    }
}
