package com.ism.gestioncommande.data.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Adresse {
    @Column(nullable = false, length = 20)
    private String quartier;
    @Column(nullable = false, length = 20)
    private String ville;
    @Column(nullable = false, length = 20)
    private String numVilla;

    @Override
    public String toString() {
        return ville + " | " + quartier + " | " + numVilla;
    }
}
