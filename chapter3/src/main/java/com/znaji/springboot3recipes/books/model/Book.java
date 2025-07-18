package com.znaji.springboot3recipes.books.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;
import java.util.Objects;

public record Book(String isbn,
                   @JacksonXmlProperty(localName = "title")
                   @JsonProperty(value = "title")
                   String name,
                   List<String> authors) {

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        return Objects.equals(isbn, ((Book) obj).isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}
