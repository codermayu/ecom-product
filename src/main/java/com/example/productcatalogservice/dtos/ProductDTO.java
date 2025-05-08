package com.example.productcatalogservice.dtos;

import com.example.productcatalogservice.models.BaseModel;
import com.example.productcatalogservice.models.Category;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO extends BaseModel {

    private String description;
    private String name;
    private double price;
    private Category category;
    private String imageUrl;
}
