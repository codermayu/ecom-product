package com.example.productcatalogservice.services;


import com.example.productcatalogservice.dtos.CategoryDTO;
import com.example.productcatalogservice.models.Category;

import java.util.List;

public interface ICategoryService {

    List<Category> getAllCategories();
    Category getCategoryById(long id);
    void deleteCategoryById(long id);
    Category createCategory(CategoryDTO categoryDTO);
    Category updateCategory(long categoryId, CategoryDTO categoryDTO);
}
