package com.ism.gestioncommande.api.controllers;

import com.ism.gestioncommande.web.dto.request.PanierDto;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Map;

public interface CommandeRestController {
    @GetMapping("/client/{id}")
    ResponseEntity<?> listerClientCommande(
                                @PathVariable Long id,
                                @RequestParam(required = false) String date,
                                @RequestParam(defaultValue = "0")int page,
                                @RequestParam(defaultValue = "6")int size) throws ParseException;

    @GetMapping("")
    ResponseEntity<?> listerAllCommande(
                                @RequestParam(required = false) String date,
                                @RequestParam(defaultValue = "0")int page,
                                @RequestParam(defaultValue = "6")int size) throws ParseException;

    @PostMapping("")
    ResponseEntity<?> saveCommande(@RequestBody PanierDto panier);

}
