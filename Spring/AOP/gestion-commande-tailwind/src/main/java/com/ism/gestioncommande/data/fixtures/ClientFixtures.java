package com.ism.gestioncommande.data.fixtures;

import com.ism.gestioncommande.data.entities.Adresse;
import com.ism.gestioncommande.data.entities.Client;
import com.ism.gestioncommande.data.repositories.ClientRepository;
import com.ism.gestioncommande.security.data.repositories.AppRoleRepository;
import com.ism.gestioncommande.security.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

//@Component
@RequiredArgsConstructor
//@Order(value = 0)
public class ClientFixtures implements CommandLineRunner {
    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final AppRoleRepository appRoleRepository;
    @Override
    public void run(String... args) throws Exception {
        for (int i = 1; i < 21; i++) {
            Client client = new Client();
            client.setNomComplet("Nom et Prenom "+i);
            client.setTelephone("77101200"+i);
            Adresse adresse = new Adresse();
            adresse.setNumVilla("Villa 00"+i);
            adresse.setVille(i%2==0? "Dakar":"Thies");
            adresse.setQuartier("Quartier 00"+i);
            client.setAdresse(adresse);
            client.setActive(i%2==0);
            client.setLogin("client"+i);
            client.setPassword(passwordEncoder.encode("passer"));
            client.getRoles().add(appRoleRepository.getByRoleName("CLIENT"));

            clientRepository.save(client);
        }

    }
}
