package com.ism.gestioncommande.web.dto.request;

import com.ism.gestioncommande.web.dto.response.ClientResponseDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.PublicKey;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PanierDto {
    private List<ArticlePanierDto> articlesPaniers;
    private Double total;
    private ClientResponseDto client;


    public void addArticleToPanier(ArticlePanierDto article){
        int index = articlesPaniers.indexOf(article);
        double montant =0;
        if (index!=-1){
            ArticlePanierDto articleInPanier = articlesPaniers.get(index);
            articleInPanier.calculNewQuantity(article.getQuantite());
            montant = article.getQuantite()*articleInPanier.getPrix();
            articleInPanier.calculMontant(montant);

        }else {
            montant = article.getQuantite()*article.getPrix();
            article.setMontant(montant);
            articlesPaniers.add(article);
        }
        total+=montant;
    }
}

