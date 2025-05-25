package com.example.productcatalogservice.services.Impl;

import com.example.productcatalogservice.dtos.ProductDTO;
import com.example.productcatalogservice.exceptions.ProductNotFoundException;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("ThirdPartyService")
public class ThirdPartyProductService implements ProductService {


    private final RestTemplate restTemplate;

    public ThirdPartyProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    String BASE_URL = "https://fakestoreapi.com";

    @Override
    public Product addProduct(ProductDTO productDto) {
        String url = BASE_URL + "/products";
        ResponseEntity<ProductDTO> productDTO = restTemplate.postForEntity(url, productDto, ProductDTO.class);
        return changeToProduct(productDTO.getBody());
    }

    @Override
    public Product getProduct(long productId) throws ProductNotFoundException {
        String url = BASE_URL + "/products/" + productId;

        ProductDTO productDTO = restTemplate.getForObject(url, ProductDTO.class, productId);
        if (productDTO == null) {
            throw new ProductNotFoundException(productId, "product not present");
        }
        return changeToProduct(productDTO);
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
    public void updateProduct(long productId, ProductDTO productDTO) throws ProductNotFoundException {
        String url = BASE_URL + "/products/" + productId;
        restTemplate.put(url, ProductDTO.class, productId);
    }

    @Override
    public void deleteProduct(long productId) throws ProductNotFoundException {
        String url = BASE_URL + "/products/" + productId;
        restTemplate.delete(url, productId);
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
