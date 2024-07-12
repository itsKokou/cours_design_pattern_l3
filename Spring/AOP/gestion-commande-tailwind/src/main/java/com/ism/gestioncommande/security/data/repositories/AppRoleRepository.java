package com.ism.gestioncommande.security.data.repositories;

import com.ism.gestioncommande.security.data.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole getByRoleName(String roleName);

}
