package com.ism.gestioncommande.security.services;

import com.ism.gestioncommande.security.data.entities.AppRole;
import com.ism.gestioncommande.security.data.entities.AppUser;

public interface SecurityService {
    AppUser getUserByLogin(String login);
    AppUser saveUser(String login, String password);
    AppRole saveRole(String role_name);
    void AddRoleToUser(String login, String role_name);
    void removeRoleToUser(String login, String role_name);
}
