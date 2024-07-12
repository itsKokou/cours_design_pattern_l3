package com.ism.gestioncommande.web.controllers;

import com.ism.gestioncommande.web.dto.request.ArticlePanierDto;
import com.ism.gestioncommande.web.dto.request.PanierDto;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public interface PanierController {
    @PostMapping("/admin/panier/add")
    String addArticleToPanier(Model model, @Valid ArticlePanierDto articleForm, @ModelAttribute("panier") PanierDto panier);

}
