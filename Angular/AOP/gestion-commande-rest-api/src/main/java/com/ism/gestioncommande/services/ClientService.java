package com.ism.gestioncommande.services;

import com.ism.gestioncommande.data.entities.Client;
import com.ism.gestioncommande.web.dto.request.ClientCreateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService extends Service<Client,Long>{
    Page<Client> getAllClientsByTelephoneContainsAndActiveTrue(String telephone, Pageable pageable);
    Client getClientById(Long id);
    ClientCreateRequestDto addClient(ClientCreateRequestDto clientDto);

    Client getClientByTelephone(String telephone);
}
