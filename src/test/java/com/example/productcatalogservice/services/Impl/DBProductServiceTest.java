package com.example.productcatalogservice.services.Impl;

import com.example.productcatalogservice.dtos.ProductDTO;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.repositories.ProductRepository;
import com.example.productcatalogservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class DBProductServiceTest {

    @MockitoBean
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;

    @Test
    public void testAddProduct() {
        Product product = new Product();
        product.setPrice(2000);
        product.setName("test");
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName("name");
        productDTO.setPrice(2000);
        productDTO.setImageUrl("test");
        productDTO.setId(1L);
        productDTO.setDescription("description");


        when(productRepository.save(product)).thenReturn(product);
        Product actual = productService.addProduct(productDTO);
        assertEquals(product, actual);

    }

}