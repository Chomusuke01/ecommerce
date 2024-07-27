package com.ob.ecommerce.dto;

import com.ob.ecommerce.model.Product;

import java.util.List;

public class CartDto {
    private int id;
    private List<Product> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
