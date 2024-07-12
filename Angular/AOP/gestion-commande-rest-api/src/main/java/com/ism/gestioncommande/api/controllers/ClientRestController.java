package com.ism.gestioncommande.api.controllers;

import com.ism.gestioncommande.web.dto.request.ClientCreateRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

public interface ClientRestController {
        @GetMapping("/clients") //End point
        ResponseEntity<?> listerClient(
                            @RequestParam(defaultValue = "0")int page,
                            @RequestParam(defaultValue = "6")int size,
                            @RequestParam(defaultValue = "") String phone);

        // Le verbe c'est GET ou POST
        @PostMapping("/clients")
        ResponseEntity<?> saveClient(@Valid @RequestBody ClientCreateRequestDto clientDto, BindingResult bindingResult);
        //@RequestBody : Les donnees seront mis dans le body de la request et seront mappées au dto

        @GetMapping("/clients/telephone/{phone}") //End point
        ResponseEntity<?> listerClientByTelephone(@PathVariable String phone);

}
