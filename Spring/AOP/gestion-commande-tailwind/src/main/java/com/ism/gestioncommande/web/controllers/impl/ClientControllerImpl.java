package com.ism.gestioncommande.web.controllers.impl;

import com.ism.gestioncommande.data.entities.Client;
import com.ism.gestioncommande.services.ClientService;
import com.ism.gestioncommande.web.controllers.ClientController;
import com.ism.gestioncommande.web.dto.request.ClientCreateRequestDto;
import com.ism.gestioncommande.web.dto.request.PanierDto;
import com.ism.gestioncommande.web.dto.response.ClientResponseDto;
import com.ism.gestioncommande.web.dto.response.CommandeResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@SessionAttributes({"panier"})//Session
public class ClientControllerImpl implements ClientController {
    private final ClientService clientService;
    @Override
    public String listerClient(Model model, @ModelAttribute("panier") PanierDto panier,
                               @RequestParam(defaultValue = "0")int page,
                               @RequestParam(defaultValue = "6")int size,
                               @RequestParam(defaultValue = "") String phone){
        Page<Client> clients = clientService.getAllClientsByTelephoneContainsAndActiveTrue(phone,PageRequest.of(page,size));
        Page<ClientResponseDto> clientsDtos = clients.map(ClientResponseDto::toDto);
        model.addAttribute("clients",clientsDtos.getContent());
        model.addAttribute("pages",new int[clientsDtos.getTotalPages()]);
        model.addAttribute("currentPage",page);
        model.addAttribute("phone",phone);
        model.addAttribute("nbrePage",clientsDtos.getTotalPages());
        model.addAttribute("panier", InitializePanier());
        panier.getArticlesPaniers().clear();
        panier.setTotal(0.0);
        panier.setClient(null);
        return "client/client";
    }

    @Override
    public String showForm(Model model) {
        ClientCreateRequestDto clientCreateRequestDto = ClientCreateRequestDto.builder().build(); //new ClientCreateRequestDto()
        model.addAttribute("client",clientCreateRequestDto);
        return "client/form.add.client";
    }

    @Override
    public String saveClient(@Valid ClientCreateRequestDto clientDto, BindingResult bindingResult, RedirectAttributes redirectAttributes ) {

        if (bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> errors.put(fieldError.getField(),fieldError.getDefaultMessage()));
            redirectAttributes.addFlashAttribute("errors",errors);//Toujours présent jusqu'à rafraîchissement
            //Message flash
        }else{
            clientService.addClient(clientDto);
        }
        return "redirect:/client/form";
    }

    @ModelAttribute("panier")
    public PanierDto InitializePanier(){
        return new PanierDto(
                new ArrayList<>(),
                0.0,
                null
        );
    }

}
