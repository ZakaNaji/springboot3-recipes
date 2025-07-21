package com.znaji.springboot3recipes.books.controller;

import com.znaji.springboot3recipes.books.model.Book;
import com.znaji.springboot3recipes.books.service.BookService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerErrorException;
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

    @GetMapping(value = "/{isbn}", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Book> find(@PathVariable String isbn) {
        return ResponseEntity.of(bookService.find(isbn));
    }

    @GetMapping(value = "/{isbn}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> find2(@PathVariable String isbn) {
        return ResponseEntity.of(bookService.find(isbn));
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody Book book, UriComponentsBuilder uriBuiler) {
        var created = bookService.create(book);
        URI uri = uriBuiler.path("/books/{isbn}").build(created.isbn());
        return ResponseEntity.created(uri).body(created);
    }

    @GetMapping("/500")
    public void error() {
        var cause = new NullPointerException("Dummy exception");
        throw new ServerErrorException(cause.getMessage(), cause);
    }
}
