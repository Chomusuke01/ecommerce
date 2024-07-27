package com.ob.ecommerce.repository;

import com.ob.ecommerce.model.Cart;
import com.ob.ecommerce.storage.InMemoryDatabase;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CartRepository implements Repository<Cart> {

    private final InMemoryDatabase storage;

    public CartRepository(InMemoryDatabase storage) {
        this.storage = storage;
    }

    @Override
    public Cart save(Cart cart) {
        return this.storage.save(cart);
    }

    @Override
    public Optional<Cart> getById(int id) {
        return Optional.ofNullable(storage.getById(id));
    }

    @Override
    public void delete(Cart cart) {
        this.storage.delete(cart);
    }

    @Override
    public List<Cart> getAll() {
        return this.storage.getAll();
    }
}
