package com.parthibanrajasekaran;

import com.parthibanrajasekaran.exception.PriceNotFoundException;
import com.parthibanrajasekaran.model.Price;
import com.parthibanrajasekaran.model.Product;
import com.parthibanrajasekaran.service.PriceService;
import com.parthibanrajasekaran.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PriceServiceTest {
    private PriceService priceService;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        productService = mock(ProductService.class);
        priceService = new PriceService(productService);
    }

    @Test
    void getAllProducts_ProductsWithoutPrices_Success() {
        // Arrange
        Product product1 = new Product("1", "Product 1");
        Product product2 = new Product("2", "Product 2");
        Product product3 = new Product("3", "Product 3");

        List<Product> products = Arrays.asList(product1, product2, product3);

        when(productService.getAllProducts()).thenReturn(products);

        // Act
        Mono<List<Product>> result = priceService.getAllProducts();

        // Assert
        StepVerifier.create(result)
                .expectNext(products)
                .verifyComplete();
    }

    @Test
    void createPrice_ValidPrice_Success() {
        // Arrange
        Price price = new Price("1", "Product 1", 9.99);

        // Act
        Mono<Price> result = priceService.createPrice(price);

        // Assert
        StepVerifier.create(result)
                .expectNext(price)
                .verifyComplete();
    }

    @Test
    void getPriceById_ExistingId_Success() {
        // Arrange
        Price price = new Price("1", "Product 1", 9.99);
        priceService.createPrice(price);

        // Act
        Mono<Price> result = priceService.getPriceById("1");

        // Assert
        StepVerifier.create(result)
                .expectNext(price)
                .verifyComplete();
    }

    @Test
    void getPriceById_NonExistingId_Error() {
        // Arrange
        String nonExistingId = "99";

        // Act
        Mono<Price> result = priceService.getPriceById(nonExistingId);

        // Assert
        StepVerifier.create(result)
                .expectError(PriceNotFoundException.class)
                .verify();
    }

    @Test
    void updatePrice_ExistingId_Success() {
        // Arrange
        Price price = new Price("1", "Product 1", 9.99);
        Price updatedPrice = new Price("1", "Product 1 Updated", 19.99);
        priceService.createPrice(price);

        // Act
        Mono<Price> result = priceService.updatePrice("1", updatedPrice);

        // Assert
        StepVerifier.create(result)
                .expectNext(updatedPrice)
                .verifyComplete();
    }

    @Test
    void updatePrice_NonExistingId_Error() {
        // Arrange
        String nonExistingId = "99";
        Price updatedPrice = new Price("99", "Product 99", 99.99);

        // Act
        Mono<Price> result = priceService.updatePrice(nonExistingId, updatedPrice);

        // Assert
        StepVerifier.create(result)
                .expectError(PriceNotFoundException.class)
                .verify();
    }

    @Test
    void deletePrice_ExistingId_Success() {
        // Arrange
        Price price = new Price("1", "Product 1", 9.99);
        priceService.createPrice(price);

        // Act
        Mono<Void> result = priceService.deletePrice("1");

        // Assert
        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void deletePrice_NonExistingId_Error() {
        // Arrange
        String nonExistingId = "99";

        // Act
        Mono<Void> result = priceService.deletePrice(nonExistingId);

        // Assert
        StepVerifier.create(result)
                .expectError(PriceNotFoundException.class)
                .verify();
    }
}
