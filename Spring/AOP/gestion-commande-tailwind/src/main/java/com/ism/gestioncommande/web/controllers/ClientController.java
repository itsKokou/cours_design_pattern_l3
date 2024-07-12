package com.ism.gestioncommande.web.controllers;

import com.ism.gestioncommande.web.dto.request.ClientCreateRequestDto;
import com.ism.gestioncommande.web.dto.request.PanierDto;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public interface ClientController {
        @GetMapping("/admin/client")
        String listerClient(Model model,@ModelAttribute("panier") PanierDto panier,
                            @RequestParam(defaultValue = "0")int page,
                            @RequestParam(defaultValue = "6")int size,
                            @RequestParam(defaultValue = "") String phone);


        @GetMapping("/admin/client/form")
        String showForm(Model model);

        @PostMapping("/admin/client/save")
        String saveClient(ClientCreateRequestDto clientDto, BindingResult bindingResult, RedirectAttributes redirectAttributes );
}
