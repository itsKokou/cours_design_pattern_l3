package com.ism.gestioncommande.data.fixtures;

import com.ism.gestioncommande.data.entities.Categorie;
import com.ism.gestioncommande.data.repositories.CategorieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
//@Order(value = 6)
@RequiredArgsConstructor
public class CategorieFixtures implements CommandLineRunner {
    private final CategorieRepository categorieRepository;

    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i < 11; i++) {
            Categorie categorie = new Categorie();
            categorie.setLibelle("Categorie "+i);
            categorie.setActive(true);
            categorieRepository.save(categorie);
        }
    }
}
