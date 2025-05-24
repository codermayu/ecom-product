package com.example.productcatalogservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends BaseModel{

    private String description;
    private String name;
    private double price;
    @ManyToOne
    private Category category;
    private String imageUrl;
    private boolean isPrimeEligible;

}
