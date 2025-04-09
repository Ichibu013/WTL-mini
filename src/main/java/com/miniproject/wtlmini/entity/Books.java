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

    private String title;

    private String author;

    private int price;

    private String category;

    private String language;

    private String imageUrl;

    private String description;

    private String isbn;

    private String publishDate;

    private String publisher;

    private int pageCount;

    private float rating;

    private int reviews;

}
