package com.tw.bootcamp.bookshop.book;

import com.tw.bootcamp.bookshop.money.Money;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookDetailsResponse {
  private Long id;
  private String name;
  private String authorName;
  private Money price;
  private String imageUrl;
  private String thumbnailUrl;
  private Double averageRating;
  private String description;
  private Boolean availability;
}
