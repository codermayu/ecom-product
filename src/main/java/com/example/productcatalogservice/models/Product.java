package com.example.productcatalogservice.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
public class Product extends BaseModel implements Serializable {

    private String description;
    private String name;
    private double price;
    @ManyToOne
    @JsonBackReference // should be added in child side, will ignore this reference when called via product controller
    private Category category;
    private String imageUrl;

}
