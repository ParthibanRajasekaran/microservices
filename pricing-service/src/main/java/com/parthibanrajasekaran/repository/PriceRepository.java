package com.parthibanrajasekaran.repository;

import com.parthibanrajasekaran.model.Price;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class PriceRepository {
    private final List<Price> priceList = new ArrayList<>();

    public List<Price> findAll() {
        return priceList;
    }

    public Optional<Price> findById(String id) {
        return priceList.stream()
                .filter(price -> price.getId().equals(id))
                .findFirst();
    }

    public Price save(Price price) {
        priceList.add(price);
        return price;
    }

    public void delete(Price price) {
        priceList.remove(price);
    }
}
