package com.tw.bootcamp.bookshop.book;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.bootcamp.bookshop.TestSecurityConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = {TestSecurityConfig.class, BookController.class})
@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@ComponentScan(basePackages = "com.tw.bootcamp.bookshop")
public class BookControllerTest {
  @Autowired private MockMvc mockMvc;
  @MockBean BookService bookService;

  @Autowired private ObjectMapper objectMapper;

  @Test
  void shouldListAllBooksWhenPresent() throws Exception {
    List<Book> books = new ArrayList<>();
    Book book = new BookTestBuilder().build();
    books.add(book);
    when(bookService.fetchAll()).thenReturn(books);

    mockMvc
        .perform(get("/books").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(1));
    verify(bookService, times(1)).fetchAll();
  }

  @Test
  void shouldBeEmptyListWhenNoBooksPresent() throws Exception {
    when(bookService.fetchAll()).thenReturn(new ArrayList<>());

    mockMvc
        .perform(get("/books").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.length()").value(0));
    verify(bookService, times(1)).fetchAll();
  }

  @Test
  void shouldReturnBookDetailsWithBookId() throws Exception {
    BookDetailsResponse bookDetailsResponse = BookTestBuilder.buildBookDetailsResponseTestBuilder();

    when(bookService.fetchBookDetails("123")).thenReturn(bookDetailsResponse);

    mockMvc
        .perform(get("/books/123").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(objectMapper.writeValueAsString(bookDetailsResponse)));

    verify(bookService, times(1)).fetchBookDetails("123");
  }

  @Test
  void shouldThrowExceptionWhileFetchingBookWithInvalidId() throws Exception {
    BookDetailsResponse bookDetailsResponse = BookTestBuilder.buildBookDetailsResponseTestBuilder();

    when(bookService.fetchBookDetails("12345")).thenThrow(ResourceNotFoundException.class);

    mockMvc
        .perform(get("/books/12345").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());

    verify(bookService, times(1)).fetchBookDetails("12345");
  }
}
