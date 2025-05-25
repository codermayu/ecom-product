package com.example.productcatalogservice.dtos;

import com.example.productcatalogservice.models.BaseModel;
import com.example.productcatalogservice.models.Product;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryDTO extends BaseModel {

    private String name;
    private String description;
    private List<Product> products;
}
