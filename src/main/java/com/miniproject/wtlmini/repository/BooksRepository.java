package com.miniproject.wtlmini.repository;

import com.miniproject.wtlmini.entity.Books;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends MongoRepository<Books, String> {

    Books findByName(String bookName);

    Books findByAuthor(String authorName);

    Books findByPublisher(String publisherName);

    Books findByCategory(String categoryName);

}
