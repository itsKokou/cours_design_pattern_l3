package com.ism.gestioncommande.web.dto.response;

import com.ism.gestioncommande.data.entities.Adresse;
import com.ism.gestioncommande.data.entities.Client;
import com.ism.gestioncommande.data.entities.Commande;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class ClientResponseDto {
    private Long id;
    private String nomComplet;
    private String telephone;
    private Adresse adresse;

    //Mapper
    public  static ClientResponseDto toDto(Client client){
        return new ClientResponseDto(client.getId(),client.getNomComplet(),client.getTelephone(),
                client.getAdresse());
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
