package com.example.productcatalogservice.services;

import com.example.productcatalogservice.exceptions.ProductNotFoundException;
import com.example.productcatalogservice.models.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {

    Product addProduct(Product product);

    Product getProduct(long productId) throws ProductNotFoundException;

    List<Product> getProducts();

    void updateProduct(long productId, Product product) throws ProductNotFoundException;

    void deleteProduct(long productId) throws ProductNotFoundException;

    Product modifyProduct(long productId, Product product) throws ProductNotFoundException;

    Page<Product> getProductByName(String name, int pageNumber, int pageSize);

//    Product changeToProduct(ProductDTO productDTO);

}
