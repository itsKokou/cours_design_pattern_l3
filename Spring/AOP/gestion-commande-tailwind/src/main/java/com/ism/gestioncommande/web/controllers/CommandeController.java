package com.ism.gestioncommande.web.controllers;

import com.ism.gestioncommande.web.dto.request.ClientCreateRequestDto;
import com.ism.gestioncommande.web.dto.request.PanierDto;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.ParseException;
import java.util.Date;

public interface CommandeController {
    @GetMapping("/client/commandes/client/{id}")
    String listerClientCommande(Model model,
                                @PathVariable Long id,
                                @RequestParam(required = false) String date,
                                @RequestParam(defaultValue = "0")int page,
                                @RequestParam(defaultValue = "6")int size) throws ParseException;

    @GetMapping("/admin/commande")
    String listerAllCommande(Model model,
                                @RequestParam(required = false) String date,
                                @RequestParam(defaultValue = "0")int page,
                                @RequestParam(defaultValue = "6")int size) throws ParseException;

    @GetMapping("/admin/commande/form/client/{id}")
    String showForm(Model model, @PathVariable Long id,@ModelAttribute("panier") PanierDto panier);

    @GetMapping("/admin/commande/save")
    String saveCommande(Model model,@ModelAttribute("panier") PanierDto panier);

}
