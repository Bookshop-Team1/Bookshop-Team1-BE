package com.tw.bootcamp.bookshop.book;

import com.tw.bootcamp.bookshop.money.Money;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;

@ActiveProfiles("dev")
public class BookTestBuilder {
  private final Book.BookBuilder bookBuilder;

  public BookTestBuilder() {
    bookBuilder =
        Book.builder()
            .name("Harry Potter")
            .authorName("J K Rowling")
            .price(Money.rupees(300))
            .imageUrl("test-img")
            .averageRating(5.0)
            .id(123L)
            .thumbnailUrl("test-url")
            .bookCount(1L);
  }

  public static BookDetailsResponse buildBookDetailsResponseTestBuilder() {
    return BookDetailsResponse.builder()
        .name("Harry Potter")
        .authorName("J K Rowling")
        .price(Money.rupees(300))
        .imageUrl("test-img")
        .averageRating(5.0)
        .availability(true)
        .id(123L)
        .description("Harry Potter")
        .thumbnailUrl("test-url")
        .build();
  }

  public Book build() {
    return bookBuilder.build();
  }

  public BookTestBuilder withPrice(int price) {
    bookBuilder.price(Money.rupees(price));
    return this;
  }

    public BookTestBuilder withName(String name) {
        bookBuilder.name(name);
        return this;
    }

    public BookTestBuilder withBookCount(Long bookCount) {
        bookBuilder.bookCount(bookCount);
        return this;
    }
}
