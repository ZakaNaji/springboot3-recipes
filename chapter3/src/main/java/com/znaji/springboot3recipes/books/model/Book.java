package com.znaji.springboot3recipes.books.model;

import java.util.List;
import java.util.Objects;

public record Book(String isbn, String name, List<String> authors) {

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
