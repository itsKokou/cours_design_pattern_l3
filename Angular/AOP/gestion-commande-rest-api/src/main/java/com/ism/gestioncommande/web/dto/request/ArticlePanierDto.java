package com.ism.gestioncommande.web.dto.request;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticlePanierDto {
    private Long id;
    private String libelle;
    private Integer quantite=0;
    private Double prix;
    private Double montant=0.0;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArticlePanierDto that = (ArticlePanierDto) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public int calculNewQuantity(int qte){
        quantite+= qte;
        return quantite;
    }

    public double calculMontant(double mnt){
        montant+= mnt;
        return montant;
    }
}
