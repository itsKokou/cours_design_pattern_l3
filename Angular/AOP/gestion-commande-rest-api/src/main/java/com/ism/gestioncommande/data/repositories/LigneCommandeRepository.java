package com.ism.gestioncommande.data.repositories;

import com.ism.gestioncommande.data.entities.LigneCommande;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande,Long> {
}
