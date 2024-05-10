package com.tw.bootcamp.bookshop.book;

import com.tw.bootcamp.bookshop.user.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {
  @Mock private BookRepository bookRepository;

  @InjectMocks @Autowired private BookService bookService;

  @AfterEach
  void tearDown() {
    bookRepository.deleteAll();
  }

  @Test
  void shouldFetchAllBooks() {
    Book book = new BookTestBuilder().withName("title").build();
    List<Book> bookList = new ArrayList<>();
    bookList.add(book);
    when(bookRepository.findAllByOrderByNameAsc()).thenReturn(bookList);
    List<Book> books = bookService.fetchAll();

    assertEquals(1, books.size());
    assertEquals("title", books.get(0).getName());
  }

  @Test
  void shouldFetchAllBooksBeSortedByNameAscending() {
    Book wingsOfFire = new BookTestBuilder().withName("Wings of Fire").build();
    Book animalFarm = new BookTestBuilder().withName("Animal Farm").build();
    List<Book> bookList = new ArrayList<>();
    bookList.add(animalFarm);
    bookList.add(wingsOfFire);
    when(bookRepository.findAllByOrderByNameAsc()).thenReturn(bookList);

    List<Book> books = bookService.fetchAll();

    assertEquals(2, books.size());
    assertEquals("Animal Farm", books.get(0).getName());
  }

  @Test
  void shouldFetchBookDetailsById() {
    Book book = new BookTestBuilder().build();
    BookDetailsResponse bookDetailsResponse = BookTestBuilder.buildBookDetailsResponseTestBuilder();
    when(bookRepository.findById(123L)).thenReturn(Optional.of(book));
    BookDetailsResponse result = bookService.fetchBookDetails("123");

    assertEquals(bookDetailsResponse.getAuthorName(), result.getAuthorName());
    assertEquals(bookDetailsResponse.getAvailability(), result.getAvailability());
    assertEquals(bookDetailsResponse.getDescription(), result.getDescription());
    assertEquals(bookDetailsResponse.getAverageRating(), result.getAverageRating());
    assertEquals(bookDetailsResponse.getPrice(), result.getPrice());
    assertEquals(bookDetailsResponse.getImageUrl(), result.getImageUrl());
    assertEquals(bookDetailsResponse.getThumbnailUrl(), result.getThumbnailUrl());
    assertEquals(bookDetailsResponse.getName(), result.getName());
    assertEquals(bookDetailsResponse.getId(), result.getId());
  }

  @Test
  void shouldThrowExceptionForInvalidBookId() {

    when(bookRepository.findById(123L)).thenThrow(ResourceNotFoundException.class);
    assertThrows(ResourceNotFoundException.class, () -> bookService.fetchBookDetails("123"));
  }
}
