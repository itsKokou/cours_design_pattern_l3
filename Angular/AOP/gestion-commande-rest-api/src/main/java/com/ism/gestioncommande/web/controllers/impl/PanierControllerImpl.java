package com.ism.gestioncommande.web.controllers.impl;

import com.ism.gestioncommande.data.entities.Article;
import com.ism.gestioncommande.services.ArticleService;
import com.ism.gestioncommande.web.controllers.PanierController;
import com.ism.gestioncommande.web.dto.request.ArticlePanierDto;
import com.ism.gestioncommande.web.dto.request.PanierDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"panier"})//Session
public class PanierControllerImpl implements PanierController {
    private final ArticleService articleService;
    @Override
    public String addArticleToPanier(Model model, ArticlePanierDto articleForm, @ModelAttribute("panier") PanierDto panier) {
        //1----------Valider les données
        Article article = articleService.show(articleForm.getId()).orElse(null);
        if (article!=null){
            articleForm.setPrix(articleForm.getPrix()==null? article.getNouveauPrix():articleForm.getPrix());
            articleForm.setLibelle(article.getLibelle());
            panier.addArticleToPanier(articleForm);
        }
        return "redirect:/commande/form/client/"+panier.getClient().getId();
    }
}
