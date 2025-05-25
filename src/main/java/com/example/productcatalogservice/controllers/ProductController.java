package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.ProductDTO;
import com.example.productcatalogservice.exceptions.ProductNotFoundException;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/products")
public class ProductController {

    private ProductService productService;

    public ProductController(@Qualifier("DBService") ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getProductDetail/{id}")
    public ResponseEntity<Product> getProductDetails(@PathVariable("id") long productId) throws ProductNotFoundException {
            Product product = productService.getProduct(productId);
            return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/getAllProductDetails")
    public List<Product> getAllProducts() {
        return productService.getProducts();
    }

    @PostMapping("/addProduct")
    public ResponseEntity<Product> addProduct(@RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.addProduct(productDTO), HttpStatus.CREATED);

    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") long productId) throws ProductNotFoundException {
        productService.deleteProduct(productId);
        return new ResponseEntity<>("product deleted", HttpStatus.OK);

    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") long productId,
                                                 @RequestBody ProductDTO productDTO)
            throws ProductNotFoundException {
        productService.updateProduct(productId, productDTO);
        return new ResponseEntity<>("product updated", HttpStatus.OK);

    }


}
