package com.ob.ecommerce.controller;

import com.ob.ecommerce.dto.CartDto;
import com.ob.ecommerce.model.Product;
import com.ob.ecommerce.service.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.POST})
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping(value = {"/", ""})
    public ResponseEntity<CartDto> createCart() {

        CartDto cart  = cartService.createCart();
        return ResponseEntity.created(URI.create(String.format("/api/v1/cart/%s", cart.getId()))).body(cart);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CartDto> getCartById (@PathVariable int id){
        return new ResponseEntity<>(cartService.getCartById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/{cartId}/products")
    public ResponseEntity<CartDto> addProductToCart(@PathVariable int cartId, @RequestBody Product ... products){
        CartDto cart = this.cartService.addProductsToCart(cartId, products);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable int id){
        cartService.deleteCart(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
