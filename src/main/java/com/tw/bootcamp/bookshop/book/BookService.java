package com.tw.bootcamp.bookshop.book;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@NoArgsConstructor
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> fetchAll() {
        return bookRepository.findAllByOrderByNameAsc();
    }

    public Book getById(long bookId) throws BookNotFoundException {
        return findById(bookId);
    }

    private Book findById(long bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found"));
    }
}
