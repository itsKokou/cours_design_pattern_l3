package com.ism.gestioncommande.security.data.entities;

import com.ism.gestioncommande.data.entities.AbstractEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "type")
@DiscriminatorValue(value = "AppUser")
public class AppUser extends AbstractEntity {
    @Column(nullable = false, unique = true)
    private String login;
    @Column(nullable = false)
    private String password;


    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(
//            name = "users_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id")
//    )
    List<AppRole> roles = new ArrayList<>();

    public AppUser(String login, String password) {
        this.login = login;
        this.password = password;
        this.active = true;
    }
}
