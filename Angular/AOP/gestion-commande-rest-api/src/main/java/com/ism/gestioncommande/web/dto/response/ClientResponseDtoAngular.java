package com.ism.gestioncommande.web.dto.response;

import com.ism.gestioncommande.data.entities.Adresse;
import com.ism.gestioncommande.data.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class ClientResponseDtoAngular {
    private Long id;
    private String nomComplet;
    private String telephone;
    private String quartier;
    private String villa;
    private String numVilla;

    //Mapper
    public  static ClientResponseDtoAngular toDto(Client client){
        return new ClientResponseDtoAngular(client.getId(),client.getNomComplet(),client.getTelephone(),
                client.getAdresse().getQuartier(),client.getAdresse().getVille(), client.getAdresse().getNumVilla());
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
