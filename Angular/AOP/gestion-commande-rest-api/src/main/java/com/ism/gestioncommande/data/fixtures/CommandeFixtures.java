package com.ism.gestioncommande.data.fixtures;

import com.ism.gestioncommande.data.entities.*;
import com.ism.gestioncommande.data.enums.EtatCommande;
import com.ism.gestioncommande.data.repositories.CategorieRepository;
import com.ism.gestioncommande.data.repositories.ClientRepository;
import com.ism.gestioncommande.data.repositories.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

//@Component
//@Order(value = 6)
@RequiredArgsConstructor
public class CommandeFixtures implements CommandLineRunner {
    private final CommandeRepository commandeRepository;
    private final ClientRepository clientRepository;

    @Override
    public void run(String... args) throws Exception {
        List<Client> clients = clientRepository.findClientsByActive(true);
        for (int i = 1; i < 11; i++) {
            Commande commande = new Commande();
            commande.setMontant(1500.0*i);
            commande.setActive(true);
            commande.setDate(new Date());
            Adresse adresse = new Adresse();
            adresse.setNumVilla("Villa 00"+i);
            adresse.setVille(i%2==0? "Dakar":"Thies");
            adresse.setQuartier("Quartier 00"+i);
            commande.setAdresse(adresse);
            commande.setClient(clients.get(ThreadLocalRandom.current().nextInt(0,  clients.size())));
            if (i % 3 == 0) {
                commande.setEtat(EtatCommande.Encours);
            }else{
                if (i % 5 == 0) {
                    commande.setEtat(EtatCommande.Termine);
                }else{
                    if (i % 2 == 0) {
                        commande.setEtat(EtatCommande.Facture);
                    }else {
                        commande.setEtat(EtatCommande.Paye);
                    }
                }
            }
            commandeRepository.save(commande);
        }

    }
}
