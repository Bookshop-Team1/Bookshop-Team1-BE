package com.tw.bootcamp.bookshop.apiUtil;

import com.tw.bootcamp.bookshop.book.BookResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor
@Getter
public class APIResponse {
    private final HttpStatus status;
    private final List<BookResponse> data;
}
