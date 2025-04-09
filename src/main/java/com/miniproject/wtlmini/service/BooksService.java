package com.miniproject.wtlmini.service;

import com.miniproject.wtlmini.dto.books.SaveBooksDto;
import com.miniproject.wtlmini.entity.Books;
import com.miniproject.wtlmini.mapping.GenericDtoMapper;
import com.miniproject.wtlmini.repository.BooksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BooksService {

    private final GenericDtoMapper mapper;

    private final BooksRepository booksRepository;


    public BooksService(GenericDtoMapper mapper,
                        BooksRepository booksRepository) {
        this.mapper = mapper;
        this.booksRepository = booksRepository;
    }

    public String saveBooks(SaveBooksDto dto) {
        Books books = booksRepository.findByTitle(dto.getTitle());
        if (books == null) {
            Books newBook = mapper.toEntity(dto, Books.class);
            booksRepository.save(newBook);
            return newBook.getTitle();
        } else {
            throw new RuntimeException("Book name already exists");
        }
    }

    public void deleteBookById(String bookId) {
        if (bookId == null) {
            throw new RuntimeException("BookId is null");
        }
        if (booksRepository.findById(bookId).isEmpty()) {
            throw new RuntimeException("Book not found");
        }
        booksRepository.deleteById(bookId);
        log.info("Book deleted successfully with id: {}", bookId);
    }

    public List<Books> getAllBooks() {
        List<Books> books = booksRepository.findAll();
        log.info("Found {}", books);
        return books;
    }

}
