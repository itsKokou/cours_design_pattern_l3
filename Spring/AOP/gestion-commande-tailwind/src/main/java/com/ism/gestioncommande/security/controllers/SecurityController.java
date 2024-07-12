package com.ism.gestioncommande.security.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

public interface SecurityController {
    @GetMapping("/")
    String login(@AuthenticationPrincipal UserDetails userDetails);
}
