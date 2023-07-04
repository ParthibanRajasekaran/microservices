//package com.parthibanrajasekaran;
//
//import au.com.dius.pact.consumer.MessagePactBuilder;
//import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
//import au.com.dius.pact.consumer.junit5.PactConsumerTest;
//import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
//import au.com.dius.pact.consumer.junit5.PactTestFor;
//import au.com.dius.pact.core.model.RequestResponsePact;
//import au.com.dius.pact.core.model.V4Pact;
//import au.com.dius.pact.core.model.annotations.Pact;
//import au.com.dius.pact.core.model.messaging.Message;
//import au.com.dius.pact.core.model.messaging.MessagePact;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.parthibanrajasekaran.model.Price;
//import com.parthibanrajasekaran.repository.PriceRepository;
//import com.parthibanrajasekaran.service.PriceService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import java.io.IOException;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import static au.com.dius.pact.consumer.dsl.LambdaDsl.newJsonBody;
//import static org.mockito.Mockito.*;
//
//@PactConsumerTest
//public class PriceConsumerPactTest {
//
//    ObjectMapper objectMapper = new ObjectMapper();
//    PriceService priceService = mock(PriceService.class);
//    PriceRepository priceRepository = mock(PriceRepository.class);
//
//    @Pact(provider="product-service", consumer = "pricing-service")
//    public RequestResponsePact getAllPricesPact(PactDslWithProvider builder) {
//        Map<String, String> headers = new HashMap<>();
//        headers.put("Content-Type", "application/json");
//
//        return builder
//                .given("Get all prices")
//                .expectsToReceive("A request to get all prices")
//                .withContent(newJsonBody((o) -> {
//                    o.array("prices", a -> a.object(item -> item
//                            .stringValue("id", "1")
//                            .stringValue("productName", "Product1")
//                            .decimalType("price", 100.0)));
//                }).build())
//                .toPact();
//    }
//
//
//    @Test
//    @PactTestFor(pactMethod = "getAllPricesPact")
//    void getAllPrices(List<Message> messages) throws IOException {
//        String json = new String(messages.get(0).contentsAsBytes());
//        Price priceList = objectMapper.readValue(json, Price.class);
//
//        priceService.createPrice(priceList).block();
//
//        verify(priceService, times(1)).createPrice(priceList);
//    }
//}
