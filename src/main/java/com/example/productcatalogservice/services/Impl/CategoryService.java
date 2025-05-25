package com.example.productcatalogservice.services.Impl;

import com.example.productcatalogservice.dtos.CategoryDTO;
import com.example.productcatalogservice.exceptions.CategoryNotFoundException;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.repositories.CategoryRepository;
import com.example.productcatalogservice.services.ICategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {

    private final CategoryRepository categoryRepository;

    CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException(id, "Category not found")) ;
    }

    @Override
    public void deleteCategoryById(long id) {

        categoryRepository.deleteById(id);

    }

    @Override
    public Category createCategory(CategoryDTO categoryDTO) {
        Category category = convertCategoryDTOToCategory(categoryDTO);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(long categoryId, CategoryDTO categoryDTO) {
        if(categoryRepository.findById(categoryId).isPresent()) {
            categoryRepository.deleteById(categoryId);
        }
        return categoryRepository.save(convertCategoryDTOToCategory(categoryDTO));
    }

    private Category convertCategoryDTOToCategory(CategoryDTO categoryDTO) {
        Category category = new Category();
        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setProducts(categoryDTO.getProducts());
        return category;
    }
}
