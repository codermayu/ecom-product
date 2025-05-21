package com.example.productcatalogservice.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product extends BaseModel{

    private String description;
    private String name;
    private double price;
    private String category;
    private String imageUrl;
    private boolean isPrimeEligible;

}
