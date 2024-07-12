package com.ism.gestioncommande.data.repositories;

import com.ism.gestioncommande.data.entities.Commande;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

public interface CommandeRepository extends JpaRepository<Commande,Long> {
    Page<Commande>findByClientIdAndActiveTrue(Long id, Pageable pageable);
    Page<Commande>findByClientIdAndDateAndActiveTrue(Long id, Date date, Pageable pageable);
    Page<Commande> findAllByActiveTrue(Pageable pageable);
    Page<Commande>findAllByActiveTrueAndDate(Date date, Pageable pageable);
}
