package com.ob.ecommerce.service;

import com.ob.ecommerce.dto.CartDto;
import com.ob.ecommerce.exception.CartNotFoundException;
import com.ob.ecommerce.repository.Repository;
import com.ob.ecommerce.model.Cart;
import com.ob.ecommerce.model.Product;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;

@Service
public class CartServiceImpl implements CartService{

    private final Repository<Cart> repository;

    public CartServiceImpl(Repository<Cart> repository) {
        this.repository = repository;
    }

    @Override
    public CartDto createCart() {
        Cart cart = new Cart();
        return mapToDto(this.repository.save(cart));
    }

    @Override
    public CartDto getCartById(int id) {

        Cart cart = this.repository.getById(id).orElseThrow(() -> new CartNotFoundException(id));

        cart.setLastUpdated(LocalDateTime.now());
        this.repository.save(cart);

        return mapToDto(cart);
    }

    private CartDto mapToDto(Cart cart){
        CartDto cartDto = new CartDto();

        cartDto.setId(cart.getId());
        cartDto.setProducts(cart.getProducts().values().stream().toList());

        return cartDto;
    }

    @Override
    public void deleteCart(int id) {
        Cart cart = repository.getById(id).orElseThrow(() -> new CartNotFoundException(id));
        this.repository.delete(cart);
    }

    @Override
    public CartDto addProductsToCart(int cartId, Product ... products) {
        Cart cart = repository.getById(cartId).orElseThrow(() -> new CartNotFoundException(cartId));

        Arrays.stream(products).forEach(cart::addProduct);
        cart.setLastUpdated(LocalDateTime.now());
        repository.save(cart);

        return mapToDto(cart);
    }
}
