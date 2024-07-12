package com.ism.gestioncommande.services;

import com.ism.gestioncommande.data.entities.Commande;
import com.ism.gestioncommande.web.dto.request.PanierDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface CommandeService extends Service<Commande,Long>{
    Page<Commande> getByClientId(Long id, Pageable pageable);
    Page<Commande>getByClientIdAndDate(Long id, Date date, Pageable pageable);
    Page<Commande> getAll(Pageable pageable);
    Page<Commande>getByDate(Date date, Pageable pageable);
    void saveCommande(PanierDto panierDto);
}
