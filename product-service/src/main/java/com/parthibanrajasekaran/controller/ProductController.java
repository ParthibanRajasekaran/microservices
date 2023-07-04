package com.parthibanrajasekaran.controller;

import com.parthibanrajasekaran.model.Product;
import com.parthibanrajasekaran.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable String id) {
        return productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found")); //replace with more specific exception
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable String id, @RequestBody Product updatedProduct) {
        Product existingProduct = productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found")); //replace with more specific exception
        existingProduct.setName(updatedProduct.getName());
        return productService.createProduct(existingProduct);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable String id) {
        Product existingProduct = productService.getProductById(id)
                .orElseThrow(() -> new RuntimeException("Product not found")); //replace with more specific exception
        productService.deleteProduct(existingProduct);
    }
}