package com.ob.ecommerce.service;

import com.ob.ecommerce.dto.CartDto;
import com.ob.ecommerce.model.Product;


public interface CartService {
    CartDto createCart();
    CartDto getCartById(int id);
    void deleteCart(int id);
    CartDto addProductsToCart(int cartId, Product ... products);

}