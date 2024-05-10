package com.tw.bootcamp.bookshop.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@AllArgsConstructor
@Data
public class JwtResponse {
    private final String token;
    private final String message;
}
