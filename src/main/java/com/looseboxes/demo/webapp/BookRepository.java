package com.looseboxes.demo.webapp;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class BookRepository {

    private List<Book> books;

    public BookRepository() {
        this.books = new ArrayList<>();
    }

    public boolean add(Book book) {
        return this.books.add(book);
    }

    public Optional<Book> findById(Long id) {
        return this.books.stream().filter(book -> book.getId().equals(id)).limit(1).findFirst();
    }

    public List<Book> findAll() {
        return Collections.unmodifiableList(books);
    }
}
