package com.example.productcatalogservice.services.Impl;

import com.example.productcatalogservice.dtos.ProductDTO;
import com.example.productcatalogservice.exceptions.ProductNotFoundException;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("ThirdPartyService")
//@Primary
public class ThirdPartyProductService implements ProductService {


    private final RestTemplate restTemplate;
    private RedisTemplate<String, Object> redisTemplate;

    public ThirdPartyProductService(RestTemplate restTemplate, RedisTemplate<String, Object> redisTemplate) {
        this.restTemplate = restTemplate;
        this.redisTemplate = redisTemplate;
    }

    String BASE_URL = "https://fakestoreapi.com";

    @Override
    public Product addProduct(Product product) {
        String url = BASE_URL + "/products";
        ProductDTO productDto = changeToProductDTO(product);
        ResponseEntity<ProductDTO> productDTO = restTemplate.postForEntity(url, productDto, ProductDTO.class);
        return changeToProduct(productDTO.getBody());
    }

    @Override
    public Product getProduct(long productId) throws ProductNotFoundException {
        String url = BASE_URL + "/products/" + productId;
        // Check if product is in Redis cache
       Product product = (Product) redisTemplate.opsForHash().get("PRODUCTS", "PRODUCT_" + productId);
        if (product != null) {
            return product;
        }
        // If not in cache, fetch from third-party API
        ProductDTO productDTO = restTemplate.getForObject(url, ProductDTO.class, productId);
        if (productDTO == null) {
            throw new ProductNotFoundException(productId, "product not present");
        }

         product = changeToProduct(productDTO);
        redisTemplate.opsForHash().put("PRODUCTS", "PRODUCT_" + productId, product);

        return  product;

    }

    @Override
    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
        String url = BASE_URL + "/products";
        ProductDTO[] products = restTemplate.getForObject(url, ProductDTO[].class);
        for (ProductDTO productDTO : products) {
            productList.add(changeToProduct(productDTO));
        }
        return productList;

    }

    @Override
    public void updateProduct(long productId, Product product) throws ProductNotFoundException {
        String url = BASE_URL + "/products/" + productId;
        restTemplate.put(url, ProductDTO.class, productId);
    }

    @Override
    public void deleteProduct(long productId) throws ProductNotFoundException {
        String url = BASE_URL + "/products/" + productId;
        restTemplate.delete(url, productId);
    }

    @Override
    public Product modifyProduct(long productId, Product product) throws ProductNotFoundException {
        return null;
    }

    @Override
    public Page<Product> getProductByName(String title, int pageNumber, int pageSize) {
        return null;
    }

    public Product changeToProduct(ProductDTO productDTO){
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setImageUrl(productDTO.getImageUrl());
        Category category = new Category();
        category.setName(productDTO.getCategory());
        product.setCategory(category);
        return product;
    }

    public ProductDTO changeToProductDTO(Product product){
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setDescription(product.getDescription());
        productDTO.setPrice(product.getPrice());
        productDTO.setImageUrl(product.getImageUrl());
        if (product.getCategory() != null) {
            productDTO.setCategory(product.getCategory().getName());
        }
        return productDTO;
    }
}
