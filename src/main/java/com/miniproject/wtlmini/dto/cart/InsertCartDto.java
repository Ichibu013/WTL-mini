package com.miniproject.wtlmini.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InsertCartDto {

    private String userID;

    private String bookID;

    private int quantity;

}
