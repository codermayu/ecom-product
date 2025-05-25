package com.example.productcatalogservice.services.Impl;

import com.example.productcatalogservice.dtos.ProductDTO;
import com.example.productcatalogservice.exceptions.ProductNotFoundException;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.repositories.CategoryRepository;
import com.example.productcatalogservice.repositories.ProductRepository;
import com.example.productcatalogservice.services.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DBService")
public class DBProductService implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public DBProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product addProduct(ProductDTO productDTO) {
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
        categoryRepository.save(productDTO.getCategory());
        return productRepository.save(changeToProduct(productDTO));
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
    public void updateProduct(long productId, ProductDTO productDTO) throws ProductNotFoundException {
       Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException(productId,
                "product not found"));
       try {
           productRepository.delete(product);
           productRepository.save(changeToProduct(productDTO));
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

    private Product changeToProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImageUrl(productDTO.getImage());
        product.setCategory(productDTO.getCategory());
        return product;
    }
}
