package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.ProductDTO;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @GetMapping("/getProductDetails/{id}")
    public ProductDTO getProductDetails(@PathVariable("id") int productId) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productId);
        return productDTO;

    }
    @PostMapping("/addProduct")
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {

    return productDTO;
    }
}
