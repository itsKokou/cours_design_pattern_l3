package com.ism.gestioncommande.api.controllers.impl;

import com.ism.gestioncommande.api.controllers.ClientRestController;
import com.ism.gestioncommande.api.dto.RestResponseDto;
import com.ism.gestioncommande.data.entities.Client;
import com.ism.gestioncommande.services.ClientService;
import com.ism.gestioncommande.web.dto.request.ClientCreateRequestDto;
import com.ism.gestioncommande.web.dto.response.ClientResponseDto;
import com.ism.gestioncommande.web.dto.response.ClientResponseDtoAngular;
import com.ism.gestioncommande.web.dto.response.ClientResponseFormDtoAngular;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/api") //toutes les ressources vers ici vont commencer par api: regle rest full
@RestController //Controller va retourner du json
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:4200")
public class ClientRestControllerImpl implements ClientRestController {
    private final ClientService clientService;
    @Override
    public ResponseEntity<Map<Object, Object>> listerClient(int page, int size, String phone) {
        Page<Client> clients = clientService.getAllClientsByTelephoneContainsAndActiveTrue(phone, PageRequest.of(page,size));
        Page<ClientResponseDtoAngular> clientsDtos = clients.map(ClientResponseDtoAngular::toDto);
        Map<Object, Object> response =  RestResponseDto.response(clientsDtos.getContent(),new int[clientsDtos.getTotalPages()],
                clientsDtos.getNumber(),clientsDtos.getTotalElements(),clientsDtos.getTotalPages(),
                HttpStatus.OK);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Map<Object, Object>> saveClient(@Valid @RequestBody ClientCreateRequestDto clientDto, BindingResult bindingResult) {
        Map<Object, Object> response;
        if (bindingResult.hasErrors()){
            Map<String, String> errors = new HashMap<>();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            fieldErrors.forEach(fieldError -> errors.put(fieldError.getField(),fieldError.getDefaultMessage()));

            response = RestResponseDto.response(errors,HttpStatus.NOT_FOUND);
        }else{
            ClientCreateRequestDto clientCreateRequestDto = clientService.addClient(clientDto);
            response = RestResponseDto.response(clientCreateRequestDto,HttpStatus.CREATED);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> listerClientByTelephone(String phone) {
        Client clientByTelephone = clientService.getClientByTelephone(phone);
        Map<Object, Object> response;
        if(clientByTelephone!=null){
            response = RestResponseDto.response(ClientResponseFormDtoAngular.toDto(clientByTelephone),HttpStatus.OK);
        }else {
            response = RestResponseDto.response(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK) ;
    }
}
