package com.miniproject.wtlmini.controllers;

import com.miniproject.wtlmini.dto.cart.CheckoutCartDto;
import com.miniproject.wtlmini.dto.cart.InsertCartDto;
import com.miniproject.wtlmini.dto.cart.cartRequestDto;
import com.miniproject.wtlmini.entity.Cart;
import com.miniproject.wtlmini.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @Operation(summary = "Create a new cart")
    @PostMapping("/create")
    public String createCart(@RequestBody String userId) {
        userId = userId.replace("\"", "").trim();
        return cartService.createCart(userId);
    }

    @Operation(summary = "Add item to cart")
    @PatchMapping("/add-to-cart")
    public Cart addToCart(@RequestBody InsertCartDto insertCartDto) {
        return cartService.addToCart(insertCartDto);
    }

    @Operation(summary = "Delete cart")
    @PostMapping("/delete")
    public Cart deleteCart(@RequestBody CheckoutCartDto checkoutCartDto) {
        return cartService.deleteCart(checkoutCartDto);
    }

    @Operation(summary = "Cart checkout")
    @PostMapping("/checkout")
    public Cart checkoutCart(@RequestBody CheckoutCartDto checkoutCartDto) {
        return cartService.checkout(checkoutCartDto);
    }

    @Operation(summary = "Get current cart")
    @PostMapping("/current")
    public Cart getCart(@RequestBody cartRequestDto insertCartDto) {
        return cartService.getCart(insertCartDto.getUserID());
    }

    @Operation(summary = "Get all carts")
    @PostMapping("/get-all-carts")
    public List<Cart> getAllCarts(String userID) {
        return cartService.getAllCarts(userID);
    }
}
