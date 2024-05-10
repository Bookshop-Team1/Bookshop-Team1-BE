package com.tw.bootcamp.bookshop.book;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static java.util.List.of;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class BookService {
  @Autowired private BookRepository bookRepository;

  public List<Book> fetchAll() {
    return bookRepository.findAllByOrderByNameAsc();
  }

  public BookDetailsResponse fetchBookDetails(String id) {
    Book book =
        bookRepository
            .findById(Long.valueOf(id))
            .orElseThrow(
                () -> new ResourceNotFoundException("Book details not found by book id:" + id));
    return buildBookDetailsResponse(book);
  }

  private BookDetailsResponse buildBookDetailsResponse(Book book) {
    return BookDetailsResponse.builder()
        .name(book.getName())
        .authorName(book.getAuthorName())
        .price(book.getPrice())
        .averageRating(book.getAverageRating())
        .description(book.getName())
        .availability(book.getBookCount() > 0)
        .id(book.getId())
        .imageUrl(book.getImageUrl())
        .thumbnailUrl(book.getThumbnailUrl())
        .build();
  }
}
