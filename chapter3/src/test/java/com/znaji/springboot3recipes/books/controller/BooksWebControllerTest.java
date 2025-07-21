package com.znaji.springboot3recipes.books.controller;

import com.znaji.springboot3recipes.books.model.Book;
import com.znaji.springboot3recipes.books.service.BookService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BooksWebController.class)
class BooksWebControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    BookService bookService;

    @Test
    void shouldReturnBooksList() throws Exception{
        when(bookService.findAll()).thenReturn(Arrays.asList(
                new Book("123", "Spring 6 Recipes", List.
                        of("Marten Deinum", "Josh Long")),
                new Book("321", "Pro Spring MVC", List.
                        of("Marten Deinum", "Colin Yates"))));

        mockMvc.perform(MockMvcRequestBuilders.get("/books.html"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/list"))
                .andExpect(model().attribute("books", Matchers.hasSize(2)));
    }

    @Test
    void shouldReturnBookNotFoundWhenNotFound() throws Exception{
        when(bookService.find(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders
                .get("/books.html")
                .param("isbn", "1234"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/details"))
                .andExpect(model().attributeDoesNotExist("book"));
    }

    @Test
    void shouldReturnBookWhenFound() throws Exception{
        Book book = new Book("123", "Spring 6 Recipes",List.of("Marten Deinum", "Josh Long"));
        when(bookService.find(anyString())).thenReturn(Optional.of(book));

        mockMvc.perform(MockMvcRequestBuilders.get("/books.html")
                .param("isbn", "1234"))
                .andExpect(status().isOk())
                .andExpect(view().name("books/details"))
                .andExpect(model().attribute("book", Matchers.is(book)));
    }
}