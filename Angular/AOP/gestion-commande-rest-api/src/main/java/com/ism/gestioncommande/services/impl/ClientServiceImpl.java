package com.ism.gestioncommande.services.impl;

import com.ism.gestioncommande.data.entities.Client;
import com.ism.gestioncommande.data.repositories.ClientRepository;
import com.ism.gestioncommande.exceptions.EntityNotFoundException;
import com.ism.gestioncommande.services.ClientService;
import com.ism.gestioncommande.web.dto.request.ClientCreateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    @Override
    public void save(Client data) {
        clientRepository.save(data);
    }

    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Optional<Client> show(Long dataID) {
        return clientRepository.findById(dataID);
    }


    @Override
    public Page<Client> getAllClientsByTelephoneContainsAndActiveTrue(String telephone, Pageable pageable) {
        return clientRepository.findAllByTelephoneContainsAndActiveTrue(telephone,pageable);
    }

    @Override
    public Client getClientById(Long id) {

        return clientRepository.findById(id)
                .orElseThrow(()->new EntityNotFoundException("Le client n'existe pas"));
    }

    @Override
    public ClientCreateRequestDto addClient(ClientCreateRequestDto clientDto) {
        Client entity = clientDto.toEntity();
        entity.setActive(true);
        entity.setLogin("test");
        entity.setPassword("passer");
        clientRepository.save(entity);
        return ClientCreateRequestDto.toDto(entity);
    }

    @Override
    public Client getClientByTelephone(String telephone) {
        return clientRepository.findClientByTelephoneAndActiveTrue(telephone);
    }
}
