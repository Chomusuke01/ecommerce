package com.ob.ecommerce.storage;

import com.ob.ecommerce.model.Cart;
import com.ob.ecommerce.util.IDGenerator;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class InMemoryDatabase {

    private final Map<Integer, Cart> cartDatabase = new ConcurrentHashMap<>();

    public Cart save(Cart cart){
        if (cart.getId() == null){
            cart.setId(IDGenerator.generateID());
        }
        this.cartDatabase.put(cart.getId(), cart);

        return cart;
    }

    public Cart getById(int id){
        return this.cartDatabase.get(id);
    }

    public void delete(Cart cart) {
        this.cartDatabase.remove(cart.getId());
    }

    public List<Cart> getAll() {
        return this.cartDatabase.values().stream().toList();
    }
}
