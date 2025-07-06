package com.example.productcatalogservice.services.Impl;

import com.example.productcatalogservice.exceptions.ProductNotFoundException;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.repositories.CategoryRepository;
import com.example.productcatalogservice.repositories.ProductRepository;
import com.example.productcatalogservice.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DBService")
//@Primary
public class DBProductService implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public DBProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product addProduct(Product product) {
        //payload
//        {
//            "description": "it is sunglass",
//                "name": "rayban",
//                "price": 22,
//                "category": {
//            "name": "sunglass",
//                    "description": "rayban sunglass"
//        }
//        }
//        Product product = changeToProduct(Product);
        return productRepository.save(product);
    }

    @Override
    public Product getProduct(long productId) throws ProductNotFoundException {
        return productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException(productId,
                "product not found"));
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    @Override
    public void updateProduct(long productId, Product product) throws ProductNotFoundException {
       Product dbproduct = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException(productId,
                "product not found"));
       try {
           productRepository.delete(dbproduct);
           productRepository.save(product);
       }catch (Exception e){
           throw new RuntimeException("issue while updating product: " + e.getMessage());
       }

    }

    @Override
    public void deleteProduct(long productId) throws ProductNotFoundException {
        Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException(productId,
                "product not found"));
        productRepository.delete(product);

    }

    @Override
    public Product modifyProduct(long productId, Product product) throws ProductNotFoundException {
//        Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException(
//                productId, "product not found"));
//        Product updatedProduct  = updateProductFields(roduct, product);
        return productRepository.save(product);
    }

    //page number is 0 based index, page size is number of records per page
    // and sorting is done by name in descending order and then by price in ascending order
    @Override
    public Page<Product> getProductByName(String name, int pageNumber, int pageSize) {
        return productRepository.findByNameContainsIgnoreCase(
                name,
                PageRequest.of(pageNumber, pageSize, Sort.by(Sort.Direction.DESC, "Name").by
                        (Sort.Direction.ASC, "price")));

    }

    @Override
    public String callUserServiceViaEureka(String token) {
        return token;
    }


    private Product updateProductFields(Product updatedProduct, Product originalProduct){
       if(updatedProduct.getPrice() != 0.0){
           originalProduct.setPrice(updatedProduct.getPrice());
       }
       if(updatedProduct.getName() != null){
           originalProduct.setName(updatedProduct.getName());
       }
       if(updatedProduct.getDescription() != null){
           originalProduct.setDescription(updatedProduct.getDescription());
       }
       if(updatedProduct.getImageUrl() != null){
           originalProduct.setImageUrl(updatedProduct.getImageUrl());
       }
       return originalProduct;
   }


}
