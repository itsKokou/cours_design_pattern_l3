package com.ism.gestioncommande.api.dto;

import com.ism.gestioncommande.data.entities.Article;
import com.ism.gestioncommande.data.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class ArticleResponseDto {
    private Long id;
    private String libelle;
    private Double prix;
    private Integer quantiteStock;
    private Double montant;
    private Integer quantite;

    //Mapper
    public  static ArticleResponseDto toDto(Article article){
        return new ArticleResponseDto(article.getId(),article.getLibelle(),article.getNouveauPrix(),
                article.getQteStock(),0.0,0);
    }



//    public  static ClientResponseDto toDto(Client client){
//        return ClientResponseDto
//                .builder()
//                .id(client.getId())
//                .nomComplet(client.getNomComplet())
//                .telephone(client.getTelephone())
//                .adresse(client.getAdresse())
//                .build();
//    }
}
