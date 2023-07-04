package com.parthibanrajasekaran;

import com.parthibanrajasekaran.model.Price;
import com.parthibanrajasekaran.model.Product;
import com.parthibanrajasekaran.service.PriceService;
import com.parthibanrajasekaran.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import reactor.core.publisher.Mono;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = ConsumerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private PriceService priceService;

    @MockBean
    private ProductService productService;

    private Price testPrice;
    private Product testProduct;

    @BeforeEach
    public void setUp() {
        testPrice = new Price("1", "Test Product", 100.0);
        testProduct = new Product("1", "Test Product");
    }

    @Test
    public void testGetAllProducts() {
        when(productService.getAllProducts()).thenReturn(Collections.singletonList(testProduct));
        ResponseEntity<Product[]> response = restTemplate.getForEntity("http://localhost:" + port + "/prices", Product[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testCreatePrice() {
        when(priceService.createPrice(testPrice)).thenReturn(Mono.just(testPrice));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Price> entity = new HttpEntity<>(testPrice, headers);
        ResponseEntity<Price> response = restTemplate.postForEntity("http://localhost:" + port + "/prices", entity, Price.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    public void testGetPriceById() {
        when(priceService.getPriceById(testPrice.getId())).thenReturn(Mono.just(testPrice));
        ResponseEntity<Price> response = restTemplate.getForEntity("http://localhost:" + port + "/prices/" + testPrice.getId(), Price.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testUpdatePrice() {
        when(priceService.updatePrice(testPrice.getId(), testPrice)).thenReturn(Mono.just(testPrice));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Price> entity = new HttpEntity<>(testPrice, headers);
        restTemplate.put("http://localhost:" + port + "/prices/" + testPrice.getId(), entity);
    }

    @Test
    public void testDeletePrice() {
        when(priceService.deletePrice(testPrice.getId())).thenReturn(Mono.empty());
        restTemplate.delete("http://localhost:" + port + "/prices/" + testPrice.getId());
    }
}
