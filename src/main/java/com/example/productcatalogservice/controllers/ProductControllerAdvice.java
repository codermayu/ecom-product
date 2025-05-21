package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    ResponseEntity<String> getProductNotFoundException(ProductNotFoundException e) {
        return new ResponseEntity<>("Product not found: " + e.getProductId(), HttpStatus.NOT_FOUND);
    }


}
