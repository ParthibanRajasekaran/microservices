package com.parthibanrajasekaran.controller;

import com.parthibanrajasekaran.exception.PriceNotFoundException;
import com.parthibanrajasekaran.model.Price;
import com.parthibanrajasekaran.model.Product;
import com.parthibanrajasekaran.service.PriceService;
import com.parthibanrajasekaran.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/prices")
public class PriceController {

    private final PriceService priceService;
    private final ProductService productService;

    public PriceController(PriceService priceService, ProductService productService) {
        this.priceService = priceService;
        this.productService = productService;
    }

    @GetMapping
    public Mono<List<Product>> getAllProducts() {
        return Mono.just(productService.getAllProducts());
    }

    @PostMapping
    public ResponseEntity<Mono<Price>> createPrice(@RequestBody Price price) {
        return new ResponseEntity<>(priceService.createPrice(price), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public Mono<Price> getPriceById(@PathVariable String id) {
        return priceService.getPriceById(id);
    }

    @PutMapping("/{id}")
    public Mono<Price> updatePrice(@PathVariable String id, @RequestBody Price updatedPrice) {
        return priceService.updatePrice(id, updatedPrice)
                .switchIfEmpty(Mono.error(new PriceNotFoundException("Price not found")));
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deletePrice(@PathVariable String id) {
        return priceService.deletePrice(id);
    }
}
