package com.ism.gestioncommande.security.data.entities;

import com.ism.gestioncommande.data.entities.AbstractEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "roles")
public class AppRole  extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    List<AppUser> users;

    public AppRole(String roleName) {
        this.roleName = roleName;
        this.active = true;
    }
}
