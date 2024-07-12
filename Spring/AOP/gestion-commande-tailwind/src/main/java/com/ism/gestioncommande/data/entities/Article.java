package com.ism.gestioncommande.data.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "articles")
public class Article extends AbstractEntity{
    @Column(nullable = false,unique = true,length = 30)
    private String libelle;
    @Column(nullable = false)
    private Double ancienPrix;
    @Column(nullable = false)
    private Double nouveauPrix;
    @Column(nullable = false,columnDefinition = "boolean default false")
    private Boolean promo;
    @Column(nullable = false)
    private Integer qteStock;
    @Column(nullable = false,unique = true,length = 20)
    private String photo;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Categorie categorie;
    @OneToMany(mappedBy = "article")
    private List<LigneCommande> ligneCommandes;
}
