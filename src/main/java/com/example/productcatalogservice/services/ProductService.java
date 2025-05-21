package com.example.productcatalogservice.services;

import com.example.productcatalogservice.dtos.ProductDTO;
import com.example.productcatalogservice.exceptions.ProductNotFoundException;
import com.example.productcatalogservice.models.Product;

import java.util.List;

public interface ProductService {

    Product addProduct(ProductDTO productDTO);

    Product getProduct(long productId) throws ProductNotFoundException;

    List<Product> getProducts();

    void updateProduct(long productId, ProductDTO productDTO) throws ProductNotFoundException;

    void deleteProduct(long productId) throws ProductNotFoundException;

}
