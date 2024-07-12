package com.ism.gestioncommande.security.data.repositories;

import com.ism.gestioncommande.security.data.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    AppUser findByLogin(String login);
}
