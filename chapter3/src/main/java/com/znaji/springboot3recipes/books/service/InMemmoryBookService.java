package com.znaji.springboot3recipes.books.service;

import com.znaji.springboot3recipes.books.model.Book;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemmoryBookService implements BookService {

    private final Map<String, Book> books = new ConcurrentHashMap<>();

    @PostConstruct
    public void initCache() {
        create(new Book ("9780061120084", "ToKill a Mockingbird", List.of ("HarperLee")));
        create(new Book ("9780451524935","1984",List.of ("George Orwell")));
        create(new Book ("9780618260300","The Hobbit",List.of ("J.R.R. Tolkien")));
    }

    @Override
    public Iterable<Book> findAll() {
        return books.values();
    }

    @Override
    public Book create(Book book) {
        books.put(book.isbn(), book);
        return book;
    }

    @Override
    public Optional<Book> find(String isbn) {
        return Optional.ofNullable(books.get(isbn));
    }
}
