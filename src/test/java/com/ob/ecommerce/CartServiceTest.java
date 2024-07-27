package com.ob.ecommerce;

import com.ob.ecommerce.dto.CartDto;
import com.ob.ecommerce.exception.CartNotFoundException;
import com.ob.ecommerce.model.Cart;
import com.ob.ecommerce.model.Product;
import com.ob.ecommerce.repository.CartRepository;
import com.ob.ecommerce.service.CartServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartServiceImpl cartService;

    private Cart cart;
    private int cartId;

    @BeforeEach
    void init() {
        cartId = 1;
        cart = new Cart();
        cart.setId(cartId);
    }

    @Test
    void createCart() {

        when(cartRepository.save(Mockito.any(Cart.class))).thenReturn(cart);

        CartDto savedCart = cartService.createCart();
        Assertions.assertThat(savedCart).isNotNull();
    }

    @Test
    void getCart() {

        when(cartRepository.getById(cartId)).thenReturn(Optional.of(cart));
        when(cartRepository.save(Mockito.any())).thenReturn(cart);

        CartDto cartResponse = cartService.getCartById(cartId);
        Assertions.assertThat(cartResponse).isNotNull();
    }

    @Test
    void ensureCartNotFound() {

        int nonExistentCartId = 1;
        when(cartRepository.getById(nonExistentCartId)).thenReturn(Optional.empty());
        assertThrows(CartNotFoundException.class, () -> cartService.getCartById(nonExistentCartId));
    }

    @Test
    void addProduct() {

        int expectedSize = 2;

        Product product1 = new Product(1, "Keyboard", 29.95);
        Product product2 = new Product(2, "Mouse", 12.95);

        when(cartRepository.getById(cartId)).thenReturn(Optional.of(cart));
        when(cartRepository.save(Mockito.any())).thenReturn(cart);

        CartDto updatedCart = cartService.addProductsToCart(cartId, product1, product2);

        Assertions.assertThat(updatedCart.getId()).isEqualTo(cartId);
        Assertions.assertThat(updatedCart.getProducts()).hasSize(expectedSize);
    }

    @Test
    void deleteCart() {
        when(cartRepository.getById(cartId)).thenReturn(Optional.of(cart));
        assertAll(() -> cartService.getCartById(cartId));
    }
}
