package com.ob.ecommerce.model;

public class Product {
    private final Integer id;
    private String description;
    private Double amount;

    public Product(Integer id, String description, Double amount) {
        this.id = id;
        this.description = description;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
