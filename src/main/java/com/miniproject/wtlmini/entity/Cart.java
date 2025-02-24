package com.miniproject.wtlmini.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {

    @Id
    private String id;

    @DBRef
    private List<User> userID;

    @DBRef
    private List<Books> bookID;

    private String quantity;

    private String totalPrice;

    private String status;

}
