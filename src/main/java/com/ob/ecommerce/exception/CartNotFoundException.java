package com.ob.ecommerce.exception;

public class CartNotFoundException extends RuntimeException {

    public CartNotFoundException(int id) {
        super("Cart not found with ID: " + id);
    }
}
