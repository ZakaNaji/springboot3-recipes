package com.znaji.springboot3recipes.books.controller;

import com.znaji.springboot3recipes.books.model.Book;
import com.znaji.springboot3recipes.books.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("books")
public class BooksController {

    private final BookService bookService;

    public BooksController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public Iterable<Book> all() {
        return bookService.findAll();
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Book> find(@PathVariable String isbn) {
        return ResponseEntity.of(bookService.find(isbn));
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book, UriComponentsBuilder uriBuiler) {
        var created = bookService.create(book);
        URI uri = uriBuiler.path("/books/{isbn}").build(created.isbn());
        return ResponseEntity.created(uri).body(created);
    }
}
