package com.miniproject.wtlmini.service;

import com.miniproject.wtlmini.dto.books.SaveBooksDto;
import com.miniproject.wtlmini.dto.books.SaveBooksWithoutImageDto;
import com.miniproject.wtlmini.entity.Books;
import com.miniproject.wtlmini.mapping.GenericDtoMapper;
import com.miniproject.wtlmini.repository.BooksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class BooksService {

    private final GenericDtoMapper mapper;

    private final BooksRepository booksRepository;

    private final ImageService imageService;

    public BooksService(GenericDtoMapper mapper, BooksRepository booksRepository, ImageService imageService) {
        this.mapper = mapper;
        this.booksRepository = booksRepository;
        this.imageService = imageService;
    }

    public Books saveBookWithoutImage(SaveBooksWithoutImageDto saveBooksDto) {
        Books bookToSave = mapper.toEntity(saveBooksDto, Books.class);
        bookToSave.setCoverImageID(null);
        booksRepository.save(bookToSave);
        log.info("Book saved successfully with name: {} without cover Image  ", saveBooksDto.getName());
        return getBookByName(saveBooksDto.getName());
    }

    public Books saveBooksWithImage(SaveBooksDto saveBooksDto) throws IOException {
        String imageId = saveBooksDto.getImage() != null
                ? imageService.uploadeImage(saveBooksDto.getImage())
                : null;

        Books bookToSave = mapper.toEntity(saveBooksDto, Books.class);
        bookToSave.setCoverImageID(imageId);
        booksRepository.save(bookToSave);
        log.info("Book saved successfully with name: {} with cover Image ID : {} ", saveBooksDto.getName(), imageId);
        return getBookByName(saveBooksDto.getName());

    }

    public Optional<Books> getBookById(String bookId) {
        if (bookId == null) {
            throw new RuntimeException("BookId is null");
        }
        if (booksRepository.findById(bookId).isEmpty()) {
            throw new RuntimeException("Book not found");
        }
        log.info("Book found successfully with id: {}", bookId);
        return booksRepository.findById(bookId);
    }

    public Books getBookByName(String bookName) {
        if (bookName == null) {
            throw new RuntimeException("BookName is null");
        }
        if (booksRepository.findByName(bookName) == null) {
            throw new RuntimeException("Book not found");
        }
        log.info("Book found successfully with name: {} ", bookName);
        return booksRepository.findByName(bookName);
    }

    public Books getBookByAuthorName(String authorName) {
        if (authorName == null) {
            throw new RuntimeException("AuthorName is null");
        }
        if (booksRepository.findByAuthor(authorName) == null) {
            throw new RuntimeException("Book not found");
        }
        log.info("Book found successfully with author name: {} ", authorName);
        return booksRepository.findByAuthor(authorName);
    }

    public Books getBookByPublisherName(String publisherName) {
        if (publisherName == null) {
            throw new RuntimeException("PublisherName is null");
        }
        if (booksRepository.findByPublisher(publisherName) == null) {
            throw new RuntimeException("Book not found");
        }
        log.info("Book found successfully with publisher name: {}", publisherName);
        return booksRepository.findByPublisher(publisherName);
    }

    public Books getBookByCategoryName(String categoryName) {
        if (categoryName == null) {
            throw new RuntimeException("CategoryName is null");
        }
        if (booksRepository.findByCategory(categoryName) == null) {
            throw new RuntimeException("Book not found");
        }
        log.info("Book found successfully with category name: {}", categoryName);
        return booksRepository.findByCategory(categoryName);
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

    public ResponseEntity<byte[]> getImage(String id) {
        Optional<Books> book = booksRepository.findById(id);
        if (book.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        try {
            byte[] image = imageService.getImage(book.get().getCoverImageID());
            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(image);
        } catch (IOException e) {
            log.error("Error while getting image: {}", e.getMessage());
            return ResponseEntity.notFound().build();
        }
    }

    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }

}
