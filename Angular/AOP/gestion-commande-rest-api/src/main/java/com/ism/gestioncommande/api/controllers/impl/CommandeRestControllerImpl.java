package com.ism.gestioncommande.api.controllers.impl;

import com.ism.gestioncommande.api.controllers.CommandeRestController;
import com.ism.gestioncommande.api.dto.RestResponseDto;
import com.ism.gestioncommande.data.entities.Commande;
import com.ism.gestioncommande.services.ArticleService;
import com.ism.gestioncommande.services.ClientService;
import com.ism.gestioncommande.services.CommandeService;
import com.ism.gestioncommande.web.dto.request.PanierDto;
import com.ism.gestioncommande.web.dto.response.CommandeResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

@RequestMapping("/api/commandes")
@RestController //Controller va retourner du json
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:4200")
public class CommandeRestControllerImpl implements CommandeRestController {
    private final CommandeService commandeService;
    private final ClientService clientService;
    private final ArticleService articleService;
    @Override
    public ResponseEntity<Map<Object, Object>> listerClientCommande(Long id, String date, int page, int size) throws ParseException {
        Page<Commande> commandes;
        if (date != null && !date.equals("")){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date dateVrai = formatter.parse(date);
            commandes = commandeService.getByClientIdAndDate(id,dateVrai, PageRequest.of(page,size));
        }else{
            commandes = commandeService.getByClientId(id,PageRequest.of(page,size));
        }
        Page<CommandeResponseDto> commandesDtos = commandes.map(CommandeResponseDto::toDto);
        Map<Object, Object> response =  RestResponseDto.response(commandesDtos.getContent(),new int[commandesDtos.getTotalPages()],
                commandesDtos.getNumber(),commandesDtos.getTotalElements(),commandesDtos.getTotalPages(),
                HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<Object, Object>> listerAllCommande(String date, int page, int size) throws ParseException {
        Page<Commande> commandes;
        if (date != null && !date.equals("")){
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date dateVrai = formatter.parse(date);
            commandes = commandeService.getByDate(dateVrai,PageRequest.of(page,size));
        }else{
            commandes = commandeService.getAll(PageRequest.of(page,size));
        }
        Page<CommandeResponseDto> commandesDtos = commandes.map(CommandeResponseDto::toDto);
        Map<Object, Object> response =  RestResponseDto.response(commandesDtos.getContent(),new int[commandesDtos.getTotalPages()],
                commandesDtos.getNumber(),commandesDtos.getTotalElements(),commandesDtos.getTotalPages(),
                HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<Object, Object>> saveCommande(@RequestBody PanierDto panier) {
        commandeService.saveCommande(panier);
        Long idClient = panier.getClient().getId();
        return new ResponseEntity<>(RestResponseDto.response(null,HttpStatus.NO_CONTENT), HttpStatus.OK);
    }
}
