package com.miniproject.wtlmini.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuppressWarnings("unused")
public class CheckoutCartDto {

    private String cartID;

    private String userID;

    private String bookID;

    private int quantity;

    private String totalPrice;

    private String status;
}
