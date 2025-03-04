package com.miniproject.wtlmini.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "cart")
public class Cart {

    @Id
    private String id;

    @DBRef
    private List<User> userID;

    @DBRef
    private List<Books> bookID;

    private String quantity;

    private int totalPrice;

    private String status;

}
