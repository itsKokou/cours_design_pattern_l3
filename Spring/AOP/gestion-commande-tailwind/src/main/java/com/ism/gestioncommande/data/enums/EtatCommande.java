package com.ism.gestioncommande.data.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EtatCommande {
    Encours(0),
    Termine(1),
    Facture(2),
    Paye(3);

    private final Integer indexEnumEtat;
}
