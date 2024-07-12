package com.ism.gestioncommande.security.services.impl;

import com.ism.gestioncommande.security.data.entities.AppRole;
import com.ism.gestioncommande.security.data.entities.AppUser;
import com.ism.gestioncommande.security.data.fixtures.AppRoleFixtures;
import com.ism.gestioncommande.security.data.repositories.AppRoleRepository;
import com.ism.gestioncommande.security.data.repositories.AppUserRepository;
import com.ism.gestioncommande.security.services.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService, UserDetailsService  {
    private final AppRoleRepository appRoleRepository;
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AppUser getUserByLogin(String login) {
        return appUserRepository.findByLogin(login);
    }

    @Override
    public AppUser saveUser(String login, String password) {
        AppUser user = appUserRepository.findByLogin(login);
        if(user!=null){
            throw new RuntimeException("Le User existe déjà");
        }
        user = new AppUser(login,passwordEncoder.encode(password));
        return  appUserRepository.save(user);
    }

    @Override
    public AppRole saveRole(String role_name) {
        AppRole role = appRoleRepository.getByRoleName(role_name);
        if(role!=null){
            throw new RuntimeException("Le Role existe déjà");
        }
        role = new AppRole(role_name);
        return appRoleRepository.save(role);
    }

    @Override
    public void AddRoleToUser(String login, String role_name) {
        AppUser user = appUserRepository.findByLogin(login);
        if(user==null) throw new RuntimeException("User not found");
        AppRole role = appRoleRepository.getByRoleName(role_name);
        if(role==null)throw new RuntimeException("Role not found");
        user.getRoles().add(role);
        appUserRepository.save(user);

    }

    @Override
    public void removeRoleToUser(String login, String role_name) {

    }

    //Strategie d'authentification == connexion
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findByLogin(username);
        if(appUser==null) throw  new UsernameNotFoundException("Cet User n'existe pas ");
        List<SimpleGrantedAuthority> authorities = appUser.getRoles().stream().map(appRole -> new SimpleGrantedAuthority(appRole.getRoleName())).toList();
        return new User(
                appUser.getLogin(),
                appUser.getPassword(),
                authorities
                );
    }
}
