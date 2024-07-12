package com.ism.gestioncommande.data.entities;

import com.ism.gestioncommande.security.data.entities.AppUser;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "clients")
@Builder
@DiscriminatorValue(value = "Client")
public class Client extends AppUser {
    @Column(nullable = false,length = 20)
    private String nomComplet;
    @Column(nullable = false,length = 20)
    private String telephone;
    @Embedded
    private Adresse adresse;
    @OneToMany(mappedBy = "client")
    private List<Commande> commandes;
}
