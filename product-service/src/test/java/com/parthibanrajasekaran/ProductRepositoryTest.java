package com.parthibanrajasekaran;

import com.parthibanrajasekaran.model.Product;
import com.parthibanrajasekaran.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductRepositoryTest {

    private ProductRepository productRepository;

    @BeforeEach
    public void setup() {
        productRepository = new ProductRepository();
    }

    @Test
    public void testFindAll() {
        Product product1 = new Product("1", "Product1");
        Product product2 = new Product("2", "Product2");
        productRepository.save(product1);
        productRepository.save(product2);

        List<Product> products = productRepository.findAll();
        assertEquals(2, products.size());
        assertTrue(products.contains(product1));
        assertTrue(products.contains(product2));
    }

    @Test
    public void testFindById() {
        Product product = new Product("1", "Product1");
        productRepository.save(product);

        Optional<Product> foundProduct = productRepository.findById("1");
        assertTrue(foundProduct.isPresent());
        assertEquals(product, foundProduct.get());
    }

    @Test
    public void testSave() {
        Product product = new Product("1", "Product1");
        productRepository.save(product);

        Optional<Product> savedProduct = productRepository.findById("1");
        assertTrue(savedProduct.isPresent());
        assertEquals(product, savedProduct.get());
    }

    @Test
    public void testDelete() {
        Product product = new Product("1", "Product1");
        productRepository.save(product);
        productRepository.delete(product);

        Optional<Product> deletedProduct = productRepository.findById("1");
        assertTrue(deletedProduct.isEmpty());
    }
}