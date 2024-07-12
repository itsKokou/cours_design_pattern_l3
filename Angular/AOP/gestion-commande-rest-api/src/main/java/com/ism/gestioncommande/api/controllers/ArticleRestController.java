package com.ism.gestioncommande.api.controllers;

import com.ism.gestioncommande.web.dto.request.ClientCreateRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

public interface ArticleRestController {
        @GetMapping("/articles") //End point
        ResponseEntity<Map<Object, Object>> listerArticle(
                            @RequestParam(defaultValue = "0")int page,
                            @RequestParam(defaultValue = "6")int size);

        // Le verbe c'est GET ou POST
        //@PostMapping("/articles")
        //ResponseEntity<?> saveArticle(@Valid @RequestBody ClientCreateRequestDto clientDto, BindingResult bindingResult);
        //@RequestBody : Les donnees seront mis dans le body de la request et seront mappées au dto

        @GetMapping("/articles/libelle/{lib}") //End point
        ResponseEntity<Map<Object, Object>> listerArticleByLibelle(@PathVariable String lib);

}
