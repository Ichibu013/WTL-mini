package com.miniproject.wtlmini.controllers;

import com.miniproject.wtlmini.dto.cart.InsertCartDto;
import com.miniproject.wtlmini.entity.Cart;
import com.miniproject.wtlmini.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Operation(summary = "Add item to cart")
    @PostMapping("/add-to-cart")
    public Cart addToCart(InsertCartDto insertCartDto) {
        return cartService.addToCart(insertCartDto);
    }

    @Operation(summary = "Get all carts")
    @PostMapping("/get-all-carts")
    public List<Cart> getAllCarts(String userID) {
        return cartService.getAllCarts(userID);
    }
}
