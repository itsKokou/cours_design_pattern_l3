package com.ism.gestioncommande.web.dto.request;

import com.ism.gestioncommande.data.entities.Adresse;
import com.ism.gestioncommande.data.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientCreateRequestDto {
    private Long id;
    @NotBlank(message = "Le nom est obligatoire")
    private String nomComplet;
    @NotBlank(message = "Le téléphone est obligatoire")
    @Size(min = 9, message = "Le Téléphone doit avoir au minimum 9 caractères")
    private String telephone;
    private String quartier;
    private String ville;
    private String numVilla;

    //Mapper
    public Client toEntity(){
        return Client.builder()
                .nomComplet(this.nomComplet)
                .telephone(this.telephone)
                .adresse(new Adresse(this.quartier,this.ville,this.numVilla))
                .build();
                //Client(this.nomComplet,this.telephone,new Adresse(this.quartier,this.ville,this.numVilla),null);
    }

    public static ClientCreateRequestDto toDto(Client client){
        return ClientCreateRequestDto.builder()
                .id(client.getId())
                .nomComplet(client.getNomComplet())
                .telephone(client.getTelephone())
                .quartier(client.getAdresse().getQuartier())
                .ville(client.getAdresse().getVille())
                .numVilla(client.getAdresse().getNumVilla())
                .build();
    }
}
