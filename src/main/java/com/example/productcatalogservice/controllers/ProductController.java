package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.exceptions.ProductNotFoundException;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
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
    public ResponseEntity<Product> addProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.addProduct(product), HttpStatus.CREATED);

    }

    @DeleteMapping("/deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") long productId) throws ProductNotFoundException {
        productService.deleteProduct(productId);
        return new ResponseEntity<>("product deleted", HttpStatus.OK);

    }

    @PutMapping("/updateProduct/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable("id") long productId,
                                                 @RequestBody Product product)
            throws ProductNotFoundException {
        productService.updateProduct(productId, product);
        return new ResponseEntity<>("product updated", HttpStatus.OK);

    }

    @PatchMapping("/modifyProduct/{id}")
    public ResponseEntity<Product> modifyProduct(@PathVariable("id") long productId,
                                                @RequestBody Product product)
            throws ProductNotFoundException {
        return new ResponseEntity<>(productService.modifyProduct(productId, product), HttpStatus.OK);

    }

}
