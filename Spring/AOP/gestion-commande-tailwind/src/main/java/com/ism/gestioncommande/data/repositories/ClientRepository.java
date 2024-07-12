package com.ism.gestioncommande.data.repositories;

import com.ism.gestioncommande.data.entities.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    List<Client> findClientsByActive(Boolean active);
    Page<Client> findByActiveTrue(Pageable pageable);
    Page<Client> findAllByTelephoneContainsAndActiveTrue(String telephone, Pageable pageable);
    Client findClientById(Long id);
}
