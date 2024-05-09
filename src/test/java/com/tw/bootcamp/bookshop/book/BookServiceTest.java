package com.tw.bootcamp.bookshop.book;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class BookServiceTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll();
    }

    @Test
    void shouldFetchAllBooks() {
        Book book = new BookTestBuilder().withName("title").build();
        bookRepository.save(book);

        List<Book> books = bookService.fetchAll();

        assertEquals(1, books.size());
        assertEquals("title", books.get(0).getName());
    }

    @Test
    void shouldFetchAllBooksBeSortedByNameAscending() {
        Book wingsOfFire = new BookTestBuilder().withName("Wings of Fire").build();
        Book animalFarm = new BookTestBuilder().withName("Animal Farm").build();
        bookRepository.save(wingsOfFire);
        bookRepository.save(animalFarm);

        List<Book> books = bookService.fetchAll();

        assertEquals(2, books.size());
        assertEquals("Animal Farm", books.get(0).getName());
    }

    @Test
    void shouldReturnBookGivenCorrectId() throws BookNotFoundException {
        Book wingsOfFire = new BookTestBuilder().withName("Wings of Fire").build();
        Book savedWindsOfFire = bookRepository.save(wingsOfFire);

        Book expectedBook = bookService.getById(savedWindsOfFire.getId());

        assertEquals(expectedBook.getId(), wingsOfFire.getId());
        assertEquals(expectedBook.getName(), wingsOfFire.getName());
    }

    @Test
    void shouldThrowExceptionGivenInCorrectId() {
        Book wingsOfFire = new BookTestBuilder().withName("Wings of Fire").build();
        bookRepository.save(wingsOfFire);

        Assertions.assertThrows(BookNotFoundException.class, () -> bookService.getById(1001001001L));
    }
}