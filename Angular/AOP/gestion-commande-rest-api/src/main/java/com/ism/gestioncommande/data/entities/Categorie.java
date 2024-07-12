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
@Table(name = "categories")
public class Categorie extends AbstractEntity{
    @Column(unique = true,nullable = false,length = 30)
    private String libelle;
    @OneToMany(mappedBy = "categorie")
    private List<Article> articles;
}
