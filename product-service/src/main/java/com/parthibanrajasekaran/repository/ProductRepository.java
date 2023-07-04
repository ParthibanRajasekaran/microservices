package com.parthibanrajasekaran.repository;

import com.parthibanrajasekaran.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {
    private final List<Product> productList = new ArrayList<>();

    public List<Product> findAll() {
        return productList;
    }

    public Optional<Product> findById(String id) {
        return productList.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    public Product save(Product product) {
        productList.add(product);
        return product;
    }

    public void delete(Product product) {
        productList.remove(product);
    }
}