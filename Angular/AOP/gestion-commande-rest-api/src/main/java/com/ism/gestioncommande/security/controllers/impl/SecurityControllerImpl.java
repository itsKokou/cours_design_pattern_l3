package com.ism.gestioncommande.security.controllers.impl;

import com.ism.gestioncommande.security.controllers.SecurityController;
import com.ism.gestioncommande.security.data.entities.AppUser;
import com.ism.gestioncommande.security.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class SecurityControllerImpl implements SecurityController {
    private  final SecurityService securityService;
    @Override
    public String login(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails.getAuthorities().stream().anyMatch(role->role.getAuthority().compareTo("ADMIN")==0)){
            return "redirect:/admin/client";
        }

        if (userDetails.getAuthorities().stream().anyMatch(role->role.getAuthority().compareTo("CLIENT")==0)){
            AppUser user =  securityService.getUserByLogin(userDetails.getUsername());
            return "redirect:/client/commandes/client/"+user.getId();
        }

        return "redirect:/login";
    }
}
