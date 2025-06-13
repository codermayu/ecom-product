package com.example.productcatalogservice.dtos;

import com.example.productcatalogservice.models.BaseModel;
import com.example.productcatalogservice.models.Category;
import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDTO extends BaseModel {

    @Nullable
    private String description;
    @Nullable
    private String name;
    @Nullable
    private double price;
    @Nullable
    private Category category;
    @Nullable
    private String imageUrl;
}
