package com.tw.bootcamp.bookshop.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class JwtRequest {
    private String username;
    private String password;
}
