package com.znaji.springboot3recipes.books.controller;

import com.znaji.springboot3recipes.books.model.Book;
import com.znaji.springboot3recipes.books.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BooksWebController {

    private final BookService bookService;

    public BooksWebController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("books.html")
    public String booksList(Model model) {
        Iterable<Book> all = bookService.findAll();
        model.addAttribute("books", all);
        return "books/list";
    }

    @GetMapping(value = "books.html", params = "isbn")
    public String bookDetails(@RequestParam("isbn") String isbn, Model model) {
        bookService.find(isbn)
                .ifPresent(book -> model.addAttribute("book", book));
        return "books/details";
    }
}
