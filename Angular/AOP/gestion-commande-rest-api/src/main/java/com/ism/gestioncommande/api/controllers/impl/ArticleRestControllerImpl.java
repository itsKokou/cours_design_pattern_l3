package com.ism.gestioncommande.api.controllers.impl;

import com.ism.gestioncommande.api.controllers.ArticleRestController;
import com.ism.gestioncommande.api.dto.ArticleResponseDto;
import com.ism.gestioncommande.api.dto.RestResponseDto;
import com.ism.gestioncommande.data.entities.Article;
import com.ism.gestioncommande.data.entities.Client;
import com.ism.gestioncommande.services.ArticleService;
import com.ism.gestioncommande.web.dto.response.ClientResponseFormDtoAngular;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequestMapping("/api") //toutes les ressources vers ici vont commencer par api: regle rest full
@RestController //Controller va retourner du json
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:4200")
public class ArticleRestControllerImpl implements ArticleRestController {

    private final ArticleService articleService;
    @Override
    public ResponseEntity<Map<Object, Object>> listerArticle(int page, int size) {
        return null;
    }

    @Override
    public ResponseEntity<Map<Object, Object>> listerArticleByLibelle(String lib) {
        Article article = articleService.getArticleByLibelle(lib);
        Map<Object, Object> response;
        if(article!=null){
            response = RestResponseDto.response(ArticleResponseDto.toDto(article), HttpStatus.OK);
        }else {
            response = RestResponseDto.response(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }
}
