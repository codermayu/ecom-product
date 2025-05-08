package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.ProductDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/products")
public class ProductController {

    @GetMapping("/getProductDetail/{id}")
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
