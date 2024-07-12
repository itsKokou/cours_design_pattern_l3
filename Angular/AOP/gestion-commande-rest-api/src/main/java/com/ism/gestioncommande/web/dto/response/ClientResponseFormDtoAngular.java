package com.ism.gestioncommande.web.dto.response;

import com.ism.gestioncommande.data.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@Builder
public class ClientResponseFormDtoAngular {
    private Long id;
    private String nomComplet;
    private String telephone;
    private String adresse;

    //Mapper
    public  static ClientResponseFormDtoAngular toDto(Client client){
        return new ClientResponseFormDtoAngular(client.getId(),client.getNomComplet(),client.getTelephone(),
                 client.getAdresse().getVille()+" | "+client.getAdresse().getQuartier() +" | " +client.getAdresse().getNumVilla());
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