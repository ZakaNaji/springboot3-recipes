package com.znaji.springboot3recipes.books.service;

import com.znaji.springboot3recipes.books.model.Book;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface BookService {

    Iterable<Book> findAll();
    Book create(Book book);
    Optional<Book> find(String isbn);
}
