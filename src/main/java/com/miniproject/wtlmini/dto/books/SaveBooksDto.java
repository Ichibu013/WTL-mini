package com.miniproject.wtlmini.dto.books;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaveBooksDto {

    private String Name;

    private String author;

    private String publisher;

    private String isbn;

    private String quantity;

    private int price;

    private String category;

    private String status;

    private String description;

    private MultipartFile image;

}
