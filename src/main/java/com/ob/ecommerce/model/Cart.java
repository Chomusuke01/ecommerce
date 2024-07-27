package com.ob.ecommerce.model;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

    private Integer id;
    private final Map<Integer, Product> products;

    private LocalDateTime lastUpdated;

    public Cart() {
        this.products = new LinkedHashMap<>();
        this.lastUpdated = LocalDateTime.now();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<Integer,Product> getProducts() {
        return products;
    }

    public void addProduct(Product product){
        this.products.put(product.getId(), product);
    }

    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
}
