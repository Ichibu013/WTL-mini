package com.miniproject.wtlmini.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "books")
public class Books {
    @Id
    private String id;

    private String name;

    private String author;

    private String publisher;

    private String description;

    private String coverImageID;

    private String isbn;

    private String category;

    private String status;

    private String quantity;

    private int price;


}
