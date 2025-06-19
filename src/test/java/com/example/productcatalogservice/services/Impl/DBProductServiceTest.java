package com.example.productcatalogservice.services.Impl;

import com.example.productcatalogservice.exceptions.ProductNotFoundException;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.repositories.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class DBProductServiceTest {

    @MockitoBean
    private ProductRepository productRepository;


    @Autowired
    private DBProductService dbProductService;

    @Test
    public void testAddProduct() {
        // Arrange
//        ProductDTO productDTO = new ProductDTO();
//        productDTO.setName("rayban");
//        productDTO.setDescription("it is sunglass");
//        productDTO.setPrice(22);
        Category category = new Category();
        category.setName("sunglass");
        category.setDescription("rayban sunglass");
//        productDTO.setCategory(category);

        Product product = new Product();
        product.setName("rayban");
        product.setDescription("it is sunglass");
        product.setPrice(22);
        product.setCategory(category);

        when(productRepository.save(any(Product.class))).thenReturn(product);

        // Act
        Product result = dbProductService.addProduct(product);

        // Assert
        assertNotNull(result);
        assertEquals(product, result);
        assertEquals("rayban", result.getName());
        assertEquals("it is sunglass", result.getDescription());
        assertEquals(22, result.getPrice());
        assertEquals("sunglass", result.getCategory().getName());
        assertEquals("rayban sunglass", result.getCategory().getDescription());
    }


    @Test
    public void testGetProduct() throws ProductNotFoundException {

        Product product = new Product();
        product.setName("rayban");
        product.setDescription("it is sunglass");
        product.setPrice(22);
        Category category = new Category();
        category.setName("sunglass");
        category.setDescription("rayban sunglass");
        product.setCategory(category);

        when(productRepository.findById(any())).thenReturn(Optional.of(product));

        Product actualProduct = dbProductService.getProduct(1L);
        assertNotNull(actualProduct);
        assertEquals(product, actualProduct);
    }

    @Test
    public void testUpdateProduct() throws ProductNotFoundException {
//        ProductDTO productDTO = new ProductDTO();
//        productDTO.setName("rayban");
//        productDTO.setDescription("it is sunglass");
//        productDTO.setPrice(22);
        Category category = new Category();
        category.setName("sunglass");
        category.setDescription("rayban sunglass");
//        productDTO.setCategory(category);

        Product product = new Product();
        product.setName("rayban");
        product.setDescription("it is sunglass");
        product.setPrice(22);
        product.setCategory(category);

        when(productRepository.findById(any())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);
        dbProductService.updateProduct(1L, product);
        Product updatedProduct = dbProductService.getProduct(1L);
        assertNotNull(updatedProduct);
        assertEquals("rayban", updatedProduct.getName());
        assertEquals("it is sunglass", updatedProduct.getDescription());
        assertEquals(22, updatedProduct.getPrice());
        assertEquals("sunglass", updatedProduct.getCategory().getName());
        assertEquals("rayban sunglass", updatedProduct.getCategory().getDescription());
    }

    @Test
    public  void testDeleteProduct() throws ProductNotFoundException {
        Product product = new Product();
        product.setName("rayban");
        product.setDescription("it is sunglass");
        product.setPrice(22);
        Category category = new Category();
        category.setName("sunglass");
        category.setDescription("rayban sunglass");
        product.setCategory(category);

        when(productRepository.findById(any())).thenReturn(Optional.of(product));

        dbProductService.deleteProduct(1L);
        when(productRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> dbProductService.getProduct(1L));
    }
}