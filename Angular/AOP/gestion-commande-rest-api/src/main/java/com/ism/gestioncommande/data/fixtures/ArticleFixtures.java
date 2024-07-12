package com.ism.gestioncommande.data.fixtures;

import com.ism.gestioncommande.data.entities.Article;
import com.ism.gestioncommande.data.entities.Categorie;
import com.ism.gestioncommande.data.repositories.ArticleRepository;
import com.ism.gestioncommande.data.repositories.CategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//@Component
//@Order(value = 4)
//Order() == ordre de chargement des fixtures 0...22222222
@RequiredArgsConstructor
public class ArticleFixtures implements CommandLineRunner {
    private final CategorieRepository categorieRepository;
    private final ArticleRepository articleRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Categorie> categories = categorieRepository.findAll();
        for (int i = 1; i < 16; i++) {
            Article article = new Article();
            article.setLibelle("Article "+i);
            article.setPhoto("photo"+i);
            article.setActive(true);
            article.setQteStock(10+i);
            article.setCategorie(categories.get(ThreadLocalRandom.current().nextInt(0,  categories.size())));
            if (i % 2 == 0) {
                article.setPromo(false);
                article.setAncienPrix(150.0*i);
                article.setNouveauPrix(150.0*i);
            }else {
                article.setPromo(true);
                article.setAncienPrix(150.0*i);
                article.setNouveauPrix(100.0*i);
            }
            articleRepository.save(article);
        }
    }
}
