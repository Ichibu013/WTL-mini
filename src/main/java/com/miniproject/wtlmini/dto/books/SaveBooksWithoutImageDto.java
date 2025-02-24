package com.miniproject.wtlmini.dto.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveBooksWithoutImageDto {

    private String name;

    private String author;

    private String publisher;

    private String isbn;

    private String quantity;

    private int price;

    private String category;

    private String status;

    private String description;

}
