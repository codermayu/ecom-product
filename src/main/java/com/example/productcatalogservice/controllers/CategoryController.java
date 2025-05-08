package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.models.Category;
import org.springframework.web.bind.annotation.*;

@RestController
public class CategoryController {

    @GetMapping("getCategoryDetails/{id}")
    public Category getCategory(@PathVariable("id") int categoryId) {
        Category category = new Category();
        category.setName("saas");
        return category;
    }

    @PostMapping("addCategory")
    public  Category addCategory(@RequestBody Category category) {
        return category;
    }
}
