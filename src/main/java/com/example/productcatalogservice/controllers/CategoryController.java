package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.CategoryDTO;
import com.example.productcatalogservice.exceptions.CategoryNotFoundException;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.services.Impl.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/category")
public class CategoryController {

    private CategoryService categoryService;

    CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/getCategoryDetails/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable("id") int categoryId) throws CategoryNotFoundException {
        return new ResponseEntity<>(categoryService.getCategoryById(categoryId), HttpStatus.OK);
    }

    @GetMapping("/getAllCategories")
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }

    @PostMapping("/addCategory")
    public ResponseEntity<Category> addCategory(@RequestBody CategoryDTO category) {
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }

    @PutMapping("/updateCategory/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") long categoryId, @RequestBody CategoryDTO category) {
        return new ResponseEntity<>(categoryService.updateCategory(categoryId, category), HttpStatus.OK);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") long id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>("category deleted", HttpStatus.OK);
    }
}
