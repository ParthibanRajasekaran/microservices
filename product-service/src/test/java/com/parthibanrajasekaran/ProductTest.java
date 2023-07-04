package com.parthibanrajasekaran;

import com.parthibanrajasekaran.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductTest {

    private Product product;

    @BeforeEach
    public void setUp() {
        product = new Product();
    }

    @Test
    public void testSetAndGetId() {
        String id = "1";
        product.setId(id);
        assertEquals(id, product.getId());
    }

    @Test
    public void testSetAndGetName() {
        String name = "Product1";
        product.setName(name);
        assertEquals(name, product.getName());
    }
}