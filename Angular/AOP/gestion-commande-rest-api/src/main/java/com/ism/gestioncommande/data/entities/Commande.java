package com.ism.gestioncommande.data.entities;

import com.ism.gestioncommande.data.enums.EtatCommande;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "commandes")
public class Commande extends AbstractEntity{
    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Column(nullable = false)
    private Double montant;
    @Enumerated(value = EnumType.STRING)
    private EtatCommande etat;
    @Embedded
    private Adresse adresse;
    @OneToMany(mappedBy = "commande")
    private List<LigneCommande> ligneCommandes;
    @ManyToOne
    @JoinColumn(nullable = false)
    private Client client;
}
