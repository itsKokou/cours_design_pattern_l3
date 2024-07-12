package com.ism.gestioncommande.data.repositories;

import com.ism.gestioncommande.data.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {
}
