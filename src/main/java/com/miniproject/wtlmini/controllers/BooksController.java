package com.miniproject.wtlmini.controllers;

import com.miniproject.wtlmini.dto.books.SaveBooksDto;
import com.miniproject.wtlmini.entity.Books;
import com.miniproject.wtlmini.service.BooksService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @Operation(summary = "Delete book by id")
    @PostMapping("/delete")
    public void deleteBookById(String bookId) {
        booksService.deleteBookById(bookId);
    }

    @Operation(summary = "Save book")
    @PostMapping(value = "/add")
    public String saveBooks(@RequestBody SaveBooksDto saveBooksDto) throws IOException {
        return booksService.saveBooks(saveBooksDto);
    }

    @Operation(summary = "Get all Books")
    @GetMapping("/all")
    public List<Books> getAllBooks() {
        return booksService.getAllBooks();
    }

}
