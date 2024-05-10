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
@NoArgsConstructor
@AllArgsConstructor
public class BookService {
    @Autowired private BookRepository bookRepository;


  public List<Book> fetchAll() {
    return bookRepository.findAllByOrderByNameAsc();
  }
    public Book getById(long bookId) throws BookNotFoundException {
        return findById(bookId);
    }

    private Book findById(long bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found"));
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
