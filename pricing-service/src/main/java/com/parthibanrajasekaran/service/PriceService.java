package com.parthibanrajasekaran.service;

import com.parthibanrajasekaran.exception.PriceNotFoundException;
import com.parthibanrajasekaran.model.Price;
import com.parthibanrajasekaran.model.Product;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceService {
    private final Map<String, Price> priceMap = new HashMap<>();
    private final ProductService productService;

    public PriceService(ProductService productService) {
        this.productService = productService;
    }

    public Mono<List<Product>> getAllProducts() {
        return Flux.fromIterable(productService.getAllProducts())
                .filter(product -> !priceMap.containsKey(product.getId()))
                .collectList();
    }

    public Mono<Price> createPrice(Price price) {
        priceMap.put(price.getId(), price);
        return Mono.just(price);
    }

    public Mono<Price> getPriceById(String id) {
        Price price = priceMap.get(id);
        if (price == null) {
            return Mono.error(new PriceNotFoundException("Price not found"));
        }
        return Mono.just(price);
    }

    public Mono<Price> updatePrice(String id, Price updatedPrice) {
        if (!priceMap.containsKey(id)) {
            return Mono.error(new PriceNotFoundException("Price not found"));
        }
        updatedPrice.setId(id);
        priceMap.put(id, updatedPrice);
        return Mono.just(updatedPrice);
    }

    public Mono<Void> deletePrice(String id) {
        if (!priceMap.containsKey(id)) {
            return Mono.error(new PriceNotFoundException("Price not found"));
        }
        priceMap.remove(id);
        return Mono.empty();
    }
}
