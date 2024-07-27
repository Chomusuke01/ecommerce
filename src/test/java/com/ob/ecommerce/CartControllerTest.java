package com.ob.ecommerce;

import com.ob.ecommerce.controller.CartController;
import com.ob.ecommerce.dto.CartDto;
import com.ob.ecommerce.exception.CartNotFoundException;
import com.ob.ecommerce.model.Product;
import com.ob.ecommerce.service.CartService;
import org.hamcrest.CoreMatchers;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.LinkedList;


import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(controllers = CartController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
class CartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartService cartService;

    private CartDto emptyCart;
    private CartDto cartWithProd;
    private int emptyCartId;
    private int cartWithProdId;


    @BeforeEach
    void init() {
        emptyCartId = 1;
        cartWithProdId = 2;

        emptyCart = new CartDto();
        emptyCart.setId(emptyCartId);
        emptyCart.setProducts(new LinkedList<>());

        cartWithProd = new CartDto();
        cartWithProd.setId(cartWithProdId);
        cartWithProd.setProducts(Arrays.asList(new Product(1, "Keyboard", 29.95), new Product(2, "Mouse", 12.95)));
    }

    @Test
    void createCart() throws Exception {
        int expectedSize = 0;
        when(cartService.createCart()).thenReturn(emptyCart);

        ResultActions response = mockMvc.perform(post("/api/v1/cart/"));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", Matchers.matchesPattern("/api/v1/cart/\\d")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.isA(Integer.class)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products", Matchers.hasSize(expectedSize)));
    }

    @Test
    void getCartById() throws Exception{

        int expectedSize = 2;
        when(cartService.getCartById(cartWithProdId)).thenReturn(cartWithProd);

        ResultActions response = mockMvc.perform(get("/api/v1/cart/2"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(cartWithProdId)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.products", Matchers.hasSize(expectedSize)));
    }

    @Test
    void deleteCart() throws Exception {
        doNothing().when(cartService).deleteCart(emptyCartId);

        ResultActions response = mockMvc.perform(delete("/api/v1/cart/1"));

        response.andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void ensureCartNotFound () throws Exception{
        int nonExistentCartId = 3;
        when(cartService.getCartById(nonExistentCartId)).thenThrow(CartNotFoundException.class);

        ResultActions response = mockMvc.perform(get("/api/v1/cart/3"));

        response.andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}