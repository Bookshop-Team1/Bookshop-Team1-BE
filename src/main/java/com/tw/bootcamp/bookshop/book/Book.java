package com.tw.bootcamp.bookshop.book;

import com.tw.bootcamp.bookshop.money.Money;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Entity
@Setter
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String authorName;
    @Embedded
    private Money price;
    private String imageUrl;
    private String thumbnailUrl;
    private Long bookCount;
    private String isbn;
    private String isbn13;
    private Integer originalPublicationYear;
    private String languageCode;
    private Double averageRating;

    public BookResponse toResponse() {
        return BookResponse.builder()
                .id(id)
                .name(name)
                .authorName(authorName)
                .price(price)
                .bookCount(bookCount)
                .build();
    }
}