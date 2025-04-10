package com.miniproject.wtlmini.service;

import com.miniproject.wtlmini.dto.cart.CheckoutCartDto;
import com.miniproject.wtlmini.dto.cart.InsertCartDto;
import com.miniproject.wtlmini.entity.Books;
import com.miniproject.wtlmini.entity.Cart;
import com.miniproject.wtlmini.entity.User;
import com.miniproject.wtlmini.mapping.GenericDtoMapper;
import com.miniproject.wtlmini.repository.BooksRepository;
import com.miniproject.wtlmini.repository.CartRepository;
import com.miniproject.wtlmini.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
@SuppressWarnings("unused")
public class CartService {

    private final UserRepository userRepository;

    private final BooksRepository booksRepository;

    private final CartRepository cartRepository;

    private final GenericDtoMapper mapper;

    public CartService(UserRepository userRepository,
                       BooksRepository booksRepository,
                       CartRepository cartRepository,
                       GenericDtoMapper mapper) {
        this.userRepository = userRepository;
        this.booksRepository = booksRepository;
        this.cartRepository = cartRepository;
        this.mapper = mapper;
    }

    public String createCart(String userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart newCart = new Cart();
        newCart.setUserID(user);
        newCart.setStatus("active");
        cartRepository.save(newCart);
        return newCart.getId();
    }

    public Cart addToCart(InsertCartDto insertCartDto) {

        Cart cart = cartRepository.findById(insertCartDto.getCartID())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        Books book = booksRepository.findById(insertCartDto.getBookID())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        if (cart.getBookID() == null) {
            cart.setBookID(new ArrayList<>());
        }
        cart.getBookID().add(book);
        log.info("Added {} to cart", book.getId());
        return cartRepository.save(cart); // Save and return the updated cart
    }

    public Cart deleteCart(CheckoutCartDto checkoutCartDto) {
        log.info("Checkout cart with id: {}", checkoutCartDto.getCartID());
        log.info("User id: {}", checkoutCartDto.getUserID());

        cartRepository.deleteById(checkoutCartDto.getCartID());

        User user = userRepository.findById(checkoutCartDto.getUserID())
                .orElseThrow(() -> new RuntimeException("user not found"));

        Cart cart = new Cart();
        cart.setUserID(user);
        cart.setStatus("active");
        cartRepository.save(cart);
        return cart;
    }

    public Cart checkout(CheckoutCartDto checkoutCartDto) {
        log.info("Checkout cart with id: {}", checkoutCartDto.getCartID());
        log.info("User id: {}", checkoutCartDto.getUserID());
        Cart cart = cartRepository.findById(checkoutCartDto.getCartID())
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        User user = userRepository.findById(checkoutCartDto.getUserID())
                .orElseThrow(() -> new RuntimeException("User not found"));
        cart.setStatus("checkout");
        cartRepository.save(cart);
        Cart newCart = new Cart();
        newCart.setUserID(user);
        newCart.setStatus("active");
        cartRepository.save(newCart);
        return newCart;
    }

    public List<Cart> getAllCarts(String userID) {
        return cartRepository.findAllById(Collections.singleton(userID));
    }

    public Cart getCart(String userID) {
        User user = userRepository.findById(userID)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUserIDAndStatus(user, "active");
        if (cart == null) {
            log.error("Cart not found");
            log.warn("Creating new cart");
            cart = new Cart();
            cart.setUserID(user);
            cart.setStatus("active");
            cartRepository.save(cart);
            return cart;
        } else {
            return cart;
        }
    }

}
