package com.looseboxes.demo.webapp;

import org.springframework.boot.CommandLineRunner;

public class CommandLineRunnerToAddBooks implements CommandLineRunner {

    private final BookRepository bookRepository;

    public CommandLineRunnerToAddBooks(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        addBook("Masters Speech for Servants", "Chinomso Ikwuagwu");
        addBook("Rich Dad, Poor Dad", "Robert Kiyosaki");
        addBook("1984", "George Orwell");
    }

    private void addBook(String name, String author) {
        Book book = new Book();
        book.setId((long)bookRepository.findAll().size() + 1);
        book.setName(name);
        book.setAuthor(author);
        bookRepository.add(book);
    }
}
