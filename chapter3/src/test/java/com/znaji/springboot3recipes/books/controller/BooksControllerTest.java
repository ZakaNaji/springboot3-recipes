package com.znaji.springboot3recipes.books.controller;

import com.znaji.springboot3recipes.books.model.Book;
import com.znaji.springboot3recipes.books.service.BookService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BooksController.class)
class BooksControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void shouldReturnListOfBooks() throws Exception {
        when(bookService.findAll()).thenReturn(Arrays.asList(
                new Book("123", "Spring 5 Recipes", List.
                        of("Marten Deinum", "Josh Long")),
                new Book("321", "Pro Spring MVC", List.
                        of("Marten Deinum", "Colin Yates"))));

        mockMvc.perform(MockMvcRequestBuilders.get("/books"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].isbn", Matchers.containsInAnyOrder("123", "321")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[*].title", Matchers.containsInAnyOrder("Spring 5 Recipes", "Pro Spring MVC")));
    }

    @Test
    void shouldReturn404WhenBookNotFound() throws Exception{
        when(bookService.find(anyString())).thenReturn(Optional.empty());

        mockMvc.perform(MockMvcRequestBuilders.get("/books/123"))
                .andExpect(status().isNotFound());
    }

    @Test
    void shouldReturnBookWhenFound() throws Exception{
        when(bookService.find(anyString())).thenReturn(Optional.of(new Book("123", "My Diary", List.of("Z.Naji"))));

        mockMvc.perform(MockMvcRequestBuilders.get("/books/123"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.isbn", Matchers.equalTo("123")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.title", Matchers.equalTo("My Diary")));
    }

    @Test
    void shouldAddBook() throws Exception{
        when(bookService.create(any(Book.class))).thenReturn(new Book("123", "My Diary", List.of("Z.Naji")));

        mockMvc.perform(MockMvcRequestBuilders.post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                    {
                      "isbn": "123",
                      "title": "My Diary",                   
                      "authors": [
                                     "Z.Naji"
                                 ]
                    }
                    """))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location","http://localhost/books/123"));

    }

}