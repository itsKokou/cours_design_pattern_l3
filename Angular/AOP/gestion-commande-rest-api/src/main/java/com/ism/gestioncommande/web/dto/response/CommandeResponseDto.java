package com.ism.gestioncommande.web.dto.response;

import com.ism.gestioncommande.data.entities.Adresse;
import com.ism.gestioncommande.data.entities.Commande;
import com.ism.gestioncommande.data.enums.EtatCommande;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
/* Construire un objet de maniere simple (design pattern de construction obj)
 - unicité du controller, repository, service
 - Factory : pour la construction d'objet qui emane d'héritage
 - Singleton : garantit l'unicité
 */
//Ici Entity transformer en Dto
public class CommandeResponseDto {

    private Long id;
    private Date date;
    private Double montant;
    private EtatCommande etat;
    private EtatCommande etatSuivant;
    private Adresse adresse;
    private String couleur;

    public  static CommandeResponseDto toDto(Commande commande){
        EtatCommande etatSuivant = EtatCommande.Paye;
        if (commande.getEtat()!=EtatCommande.Paye){
           Long index = commande.getEtat().getIndexEnumEtat()+1L;
           etatSuivant = EtatCommande.values()[index.intValue()];
        }
        String couleur = "rounded-md cursor-pointer border-2 border-green-600 px-6 py-1 font-medium text-green-600 transition-colors hover:bg-green-600 hover:text-white";
        if(etatSuivant ==EtatCommande.Facture){
            couleur = "rounded-md cursor-pointer border-2 border-orange-600 px-6 py-1 font-medium text-orange-600 transition-colors hover:bg-orange-600 hover:text-white";
        } else if (etatSuivant ==EtatCommande.Termine) {
            couleur = "rounded-md cursor-pointer border-2 border-yellow-500 px-6 py-1 font-medium text-yellow-500 transition-colors hover:bg-yellow-500 hover:text-white";
        }
        return CommandeResponseDto
                .builder()
                .id(commande.getId())
                .date(commande.getDate())
                .montant(commande.getMontant())
                .etat(commande.getEtat())
                .etatSuivant(etatSuivant)
                .couleur(couleur)
                .adresse(commande.getAdresse())
                .build();
    }
}
