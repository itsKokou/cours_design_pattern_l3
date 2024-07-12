package com.ism.gestioncommande.security.data.fixtures;

import com.ism.gestioncommande.data.entities.Categorie;
import com.ism.gestioncommande.data.repositories.CategorieRepository;
import com.ism.gestioncommande.security.data.entities.AppRole;
import com.ism.gestioncommande.security.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
//@Order(value = 5)
public class AppRoleFixtures implements CommandLineRunner {
    private final SecurityService securityService;

    @Override
    public void run(String... args) throws Exception {
        securityService.saveRole("ADMIN");
        securityService.saveRole("CLIENT");
    }
}
