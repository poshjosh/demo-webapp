package com.looseboxes.demo.webapp;

import com.looseboxes.ratelimiter.annotation.RateLimit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
public class BookResource implements ErrorController {

    private final Logger log = LoggerFactory.getLogger(BookResource.class);

    private BookService bookService;

    public BookResource(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/error")
    public String error(HttpServletRequest request) {
        final Object oval = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        final String sval = oval == null ? "" : oval.toString();
        return sval.isEmpty() ? "An unexpected error occurred" : sval;
    }

    @RequestMapping
    public String home() {
        return "This is the Book Service";
    }

    @PostMapping("/books")
    public boolean postBook(@RequestBody Book book) {
        log.info("REST request to post: {}", book);
        return bookService.add(book);
    }

    @GetMapping("/books/{id}")
    public Optional<Book> getBook(@PathVariable Long id) {
        log.info("REST request to get book by id: {}", id);
        return bookService.findById(id);
    }

    @RateLimit(limit = 2, duration = 1, timeUnit = TimeUnit.MINUTES)
    @RateLimit(limit = 20, duration = 5, timeUnit = TimeUnit.MINUTES)
    @GetMapping("/books")
    public List<Book> getBooks() {
        log.info("REST request to get all books");
        return bookService.findAll();
    }
}
