package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.ProductDTO;
import com.example.productcatalogservice.exceptions.ProductNotFoundException;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @MockitoBean
    private ProductService productService;

    @Autowired
    private ProductController productController;

    @Test
    public void testGetProductDetails_Success() throws ProductNotFoundException {
        long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Product 1");
        product.setDescription("Product 1");
        product.setPrice(2000);

        when(productService.getProduct(productId)).thenReturn(product);

        Product actualProduct = productController.getProductDetails(productId).getBody();

        assertEquals(product, actualProduct);
    }

    @Test
    public void testGetProductDetails_Failure() throws ProductNotFoundException {
        long productId = 1L;
        Product product = new Product();
        product.setId(productId);
        product.setName("Product 1");
        product.setDescription("Product 1");
        product.setPrice(2000);

        when(productService.getProduct(2L)).thenReturn(product);

        Product actualProduct = productController.getProductDetails(productId).getBody();

        assertNotEquals(product, actualProduct);
    }

    @Test
    public void testGetAllProducts_Success() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setDescription("Product 1");
        product.setPrice(2000);

        Product product2 = new Product();
        product.setId(2L);
        product.setName("Product 2");
        product.setDescription("Product 2");
        product.setPrice(1000);

        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product2);
        when(productService.getProducts()).thenReturn(products);
        List<Product> actualProducts = productController.getAllProducts();
        assertEquals(products, actualProducts);
    }

    @Test
    public void testGetAllProducts_Failed() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setDescription("Product 1");
        product.setPrice(2000);

        Product product2 = new Product();
        product.setId(2L);
        product.setName("Product 2");
        product.setDescription("Product 2");
        product.setPrice(1000);

        List<Product> products = new ArrayList<>();
        products.add(product);
        products.add(product2);

        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(product);

        when(productService.getProducts()).thenReturn(products);
        List<Product> actualProducts = productController.getAllProducts();

        assertNotEquals(expectedProducts, actualProducts);
    }

    @Test
    public void testAddProductSuccess() {
        Product product = new Product();
        product.setId(1L);
        product.setName("Product 1");
        product.setDescription("Product 1");
        product.setPrice(2000);

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Product 1");
        productDTO.setDescription("Product 1");
        productDTO.setPrice(2000);


        when(productService.addProduct(any(Product.class))).thenReturn(product);
        Product actualProduct = productController.addProduct(product).getBody();
        assertEquals(product, actualProduct);
    }

    @Test
   public void testModifyProductSuccess() throws ProductNotFoundException {
        long productId = 1L;
       Product product = new Product();
       product.setId(productId);
       product.setName("Product 1");
       product.setDescription("Product 1");
       product.setPrice(2000);

       ProductDTO productDTO = new ProductDTO();
       productDTO.setId(productId);
       productDTO.setName("Product 1");
       productDTO.setDescription("Product 1");
       productDTO.setPrice(2000);


       when(productService.modifyProduct(productId, product)).thenReturn(product);
       Product actualProduct = productController.modifyProduct(productId, product).getBody();
       assertEquals(product, actualProduct);
   }



}