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
    private String category;
    private String image;
}
