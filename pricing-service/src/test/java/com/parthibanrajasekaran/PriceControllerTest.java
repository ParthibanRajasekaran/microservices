package com.parthibanrajasekaran;

import com.parthibanrajasekaran.controller.PriceController;
import com.parthibanrajasekaran.exception.PriceNotFoundException;
import com.parthibanrajasekaran.model.Price;
import com.parthibanrajasekaran.model.Product;
import com.parthibanrajasekaran.service.PriceService;
import com.parthibanrajasekaran.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

class PriceControllerTest {

    @Mock
    private PriceService priceService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private PriceController priceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllProducts_ShouldReturnAllProducts() {
        List<Product> expectedProducts = Arrays.asList(
                new Product("1", "Product 1"),
                new Product("2", "Product 2")
        );
        when(productService.getAllProducts()).thenReturn(expectedProducts);

        Mono<List<Product>> result = priceController.getAllProducts();

        StepVerifier.create(result)
                .expectNext(expectedProducts)
                .verifyComplete();
    }

    @Test
    void createPrice_ShouldCreateNewPrice() {
        Price price = new Price("1", "Product 1", 9.99);
        when(priceService.createPrice(any(Price.class))).thenReturn(Mono.just(price));

        ResponseEntity<Mono<Price>> result = priceController.createPrice(price);

        // Extract the body (Mono<Price>) from the ResponseEntity for verification
        Mono<Price> resultBody = result.getBody();

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertNotNull(resultBody);

        StepVerifier.create(resultBody)
                .expectNext(price)
                .verifyComplete();
    }


    @Test
    void getPriceById_ExistingId_ShouldReturnPrice() {
        String id = "1";
        Price expectedPrice = new Price(id, "Product 1", 9.99);
        when(priceService.getPriceById(id)).thenReturn(Mono.just(expectedPrice));

        Mono<Price> result = priceController.getPriceById(id);

        StepVerifier.create(result)
                .expectNext(expectedPrice)
                .verifyComplete();
    }

    @Test
    void getPriceById_NonExistingId_ShouldReturnEmptyMono() {
        String id = "999";
        when(priceService.getPriceById(id)).thenReturn(Mono.empty());

        Mono<Price> result = priceController.getPriceById(id);

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void updatePrice_ExistingId_ShouldUpdateAndReturnPrice() {
        String id = "1";
        Price existingPrice = new Price(id, "Product 1", 9.99);
        Price updatedPrice = new Price(id, "Product 1 Updated", 19.99);
        when(priceService.updatePrice(eq(id), any(Price.class))).thenReturn(Mono.just(updatedPrice));

        Mono<Price> result = priceController.updatePrice(id, updatedPrice);

        StepVerifier.create(result)
                .expectNext(updatedPrice)
                .verifyComplete();
    }

    @Test
    void deletePrice_ExistingId_ShouldDeletePrice() {
        String id = "1";
        when(priceService.deletePrice(id)).thenReturn(Mono.empty());

        Mono<Void> result = priceController.deletePrice(id);

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void deletePrice_NonExistingId_ShouldReturnEmptyMono() {
        String id = "999";
        when(priceService.deletePrice(id)).thenReturn(Mono.error(new PriceNotFoundException("Price not found")));

        Mono<Void> result = priceController.deletePrice(id);

        StepVerifier.create(result)
                .verifyError(PriceNotFoundException.class);
    }
}
