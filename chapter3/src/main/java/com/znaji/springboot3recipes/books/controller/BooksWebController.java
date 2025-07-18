package com.znaji.springboot3recipes.books.controller;

import com.znaji.springboot3recipes.books.model.Book;
import com.znaji.springboot3recipes.books.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
