package com.miniproject.wtlmini.controllers;

import com.miniproject.wtlmini.dto.books.SaveBooksDto;
import com.miniproject.wtlmini.dto.books.SaveBooksWithoutImageDto;
import com.miniproject.wtlmini.entity.Books;
import com.miniproject.wtlmini.service.BooksService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @Operation(summary = "Delete book by id")
    @PostMapping("/delete-book-by-id")
    public void deleteBookById(String bookId) {
        booksService.deleteBookById(bookId);
    }

    @Operation(summary = "Save book without image")
    @PostMapping("/save-books-no-image")
    public Books saveBooks(@RequestBody SaveBooksWithoutImageDto saveBooksDto) {
        return booksService.saveBookWithoutImage(saveBooksDto);
    }

    @Operation(summary = "Save book with image")
    @PostMapping(value = "/save-books", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Books saveBooksWithImage(
            @RequestPart String bookName,
            @RequestPart String author,
            @RequestPart String publisher,
            @RequestPart String category,
            @RequestPart int price,
            @RequestPart String status,
            @RequestPart String description,
            @RequestPart String quantity,
            @RequestPart String isbn,
            @RequestPart(required = false) MultipartFile image
    ) throws IOException {
        try {
            SaveBooksDto saveBooksDto = new SaveBooksDto(bookName, author, publisher, isbn, category, price, status, description, quantity, image);
            return booksService.saveBooksWithImage(saveBooksDto);
        } catch (IOException e) {
            throw new IOException("Error while uploading image");
        }

    }

    @Operation(summary = "Get book by id")
    @PostMapping("/get-book-by-id")
    public Optional<Books> getBookById(String bookId) {
        return booksService.getBookById(bookId);
    }

    @Operation(summary = "Get book by name")
    @PostMapping("/get-book-by-name")
    public Books getBookByName(String bookName) {
        return booksService.getBookByName(bookName);
    }

    @Operation(summary = "Get book by author name")
    @PostMapping("/get-book-by-author")
    public Books getBookByAuthorName(String authorName) {
        return booksService.getBookByAuthorName(authorName);
    }

    @Operation(summary = "Get book by publisher name")
    @PostMapping("/get-book-by-publisher")
    public Books getBookByPublisherName(String publisherName) {
        return booksService.getBookByPublisherName(publisherName);
    }

    @Operation(summary = "Get book by category name")
    @PostMapping("/get-book-by-category")
    public Books getBookByCategoryName(String categoryName) {
        return booksService.getBookByCategoryName(categoryName);
    }

    @Operation(summary = "Get all books")
    @PostMapping("/get-all-books")
    public List<Books> getAllBooks() {
        return booksService.getAllBooks();
    }

    @Operation(summary = "Get image by id")
    @GetMapping("/{userid}/get-image")
    public ResponseEntity<byte[]> getImage(@PathVariable String userid) {
        return booksService.getImage(userid);
    }

}
