package com.parthibanrajasekaran;

import com.parthibanrajasekaran.model.Product;
import com.parthibanrajasekaran.repository.ProductRepository;
import com.parthibanrajasekaran.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    private ProductService productService;

    @BeforeEach
    public void setup() {
        productService = new ProductService(productRepository);
    }

    @Test
    public void testGetAllProducts() {
        Product product1 = new Product("1", "Product1");
        Product product2 = new Product("2", "Product2");
        List<Product> products = Arrays.asList(product1, product2);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.getAllProducts();
        assertEquals(result, products);
    }

    @Test
    public void testGetProductById() {
        Product product = new Product("1", "Product1");
        when(productRepository.findById("1")).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById("1");
        assertEquals(result.get(), product);
    }

    @Test
    public void testCreateProduct() {
        Product product = new Product("1", "Product1");
        when(productRepository.save(product)).thenReturn(product);

        Product result = productService.createProduct(product);
        assertEquals(result, product);
    }

    @Test
    public void testDeleteProduct() {
        Product product = new Product("1", "Product1");
        doNothing().when(productRepository).delete(product);

        productService.deleteProduct(product);
        verify(productRepository, times(1)).delete(product);
    }
}