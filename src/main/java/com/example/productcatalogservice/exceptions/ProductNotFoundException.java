package com.example.productcatalogservice.exceptions;

import lombok.Data;

@Data
public class ProductNotFoundException extends Exception{
    long productId;

    public ProductNotFoundException(String message){
        super(message);
    }
    public ProductNotFoundException(long productId, String message){
        super(message);
        this.productId = productId;
    }
}
