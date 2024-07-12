package com.ism.gestioncommande.api.dto;

import org.springframework.http.HttpStatus;

import java.util.HashMap;
import java.util.Map;

public class RestResponseDto {
    public static Map<Object,Object> response(Object results, Object pages,
                                                      Object currentPage, Object totalItems,
                                                      Object totalPages, HttpStatus status){
        Map<Object, Object> response = new HashMap<>();
        response.put("status", status.value());
        response.put("results", results);
        response.put("pages",pages);
        response.put("currentPage",currentPage); //=>model.put("currentPage",page);
        response.put("totalItems",totalItems);
        response.put("totalPages",totalPages);
        return  response;
    }

    public static Map<Object,Object> response(Object results, HttpStatus status){
        Map<Object, Object> response = new HashMap<>();
        response.put("status", status.value());
        response.put("results", results);
        return  response;
    }
}
