package com.ob.ecommerce.service;

import com.ob.ecommerce.dto.CartDto;
import com.ob.ecommerce.exception.CartNotFoundException;
import com.ob.ecommerce.exception.NotValidProductException;
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
        cart.setLastUpdated(LocalDateTime.now());
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

        Arrays.stream(products).forEach(product -> this.addProduct(product, cart));
        cart.setLastUpdated(LocalDateTime.now());
        repository.save(cart);

        return mapToDto(cart);
    }

    private void addProduct(Product product, Cart cart) {
        checkProduct(product);
        cart.addProduct(product);
    }

    private void checkProduct(Product product) {
        if (product.getId() == null){
            throw new NotValidProductException("ID");
        }
        if (product.getDescription() == null) {
            throw new NotValidProductException("Description");
        }
        if (product.getAmount() == null){
            throw new NotValidProductException("Amount");
        }
    }
}
