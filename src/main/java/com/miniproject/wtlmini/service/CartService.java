package com.miniproject.wtlmini.service;

import com.miniproject.wtlmini.dto.cart.InsertCartDto;
import com.miniproject.wtlmini.entity.Books;
import com.miniproject.wtlmini.entity.Cart;
import com.miniproject.wtlmini.entity.User;
import com.miniproject.wtlmini.mapping.GenericDtoMapper;
import com.miniproject.wtlmini.repository.CartRepository;
import com.miniproject.wtlmini.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@SuppressWarnings("unused")
public class CartService {

    private final UserRepository userRepository;

    private final BooksService booksService;

    private final CartRepository cartRepository;

    private final GenericDtoMapper mapper;

    public CartService(UserRepository userRepository,
                       BooksService booksService,
                       CartRepository cartRepository,
                       GenericDtoMapper mapper) {
        this.userRepository = userRepository;
        this.booksService = booksService;
        this.cartRepository = cartRepository;
        this.mapper = mapper;
    }

    public Cart addToCart(InsertCartDto insertCartDto) {
        User user = userRepository.findById(insertCartDto.getUserID())
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<User> userList = new ArrayList<>(Collections.singletonList(user));

        Books book = booksService.getBookById(insertCartDto.getBookID())
                .orElseThrow(() -> new RuntimeException("Book not found"));

        List<Books> booksList = new ArrayList<>(Collections.singleton(book));

        Cart checkoutCart = mapper.toEntity(insertCartDto, Cart.class);

        checkoutCart.setUserID(userList);
        checkoutCart.setBookID(booksList);

        checkoutCart.setStatus("Checked Out");
        checkoutCart.setTotalPrice(String.valueOf(book.getPrice() * insertCartDto.getQuantity()));

        log.info("Item added to cart: {}", checkoutCart);

        cartRepository.save(checkoutCart);

        return checkoutCart;
    }

    public List<Cart> getAllCarts(String userID) {
        return cartRepository.findAllById(Collections.singleton(userID));
    }
}
