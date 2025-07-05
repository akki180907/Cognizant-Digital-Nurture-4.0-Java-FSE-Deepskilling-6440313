package com.library.service;

import com.library.model.Book;
import com.library.repository.BookRepository;
import java.util.List;

public class BookService {
    private BookRepository bookRepository;

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void serve() {
        List<Book> books = bookRepository.findAll(); // JPA method
        books.forEach(book -> System.out.println("Serving: " + book.getTitle()));
    }
}
