package com.miniproject.wtlmini.dto.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveBooksDto {

    private String id;

    private String title;

    private String author;

    private int price;

    private String category;

    private String language;

    private String imageUrl;

    private String description;

    private String isbn;

    private String publishedDate;

    private String publisher;

    private int pageCount;

    private float rating;

    private int reviewCount;

}
