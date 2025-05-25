package com.example.productcatalogservice.exceptions;

import lombok.Data;

@Data
public class CategoryNotFoundException extends RuntimeException {

    private long categoryId;

    public CategoryNotFoundException(String message) {
        super(message);
    }

    public CategoryNotFoundException(long categoryId, String message) {
        super(message);
        this.categoryId = categoryId;
    }
}
