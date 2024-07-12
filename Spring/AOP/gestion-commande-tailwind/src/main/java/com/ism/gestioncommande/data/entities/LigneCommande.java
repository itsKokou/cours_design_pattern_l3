package com.ism.gestioncommande.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Ligne_commandes")
public class LigneCommande extends AbstractEntity{
    @Column(nullable = false)
    private Double prix;
    @Column(nullable = false)
    private Integer qte;
    @Column(nullable = false)
    private Double montant;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Article article;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Commande commande;
}
