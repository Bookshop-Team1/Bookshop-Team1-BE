package com.tw.bootcamp.bookshop.utils;

import com.tw.bootcamp.bookshop.user.Role;
import com.tw.bootcamp.bookshop.user.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class JwtUtilTest {
    @InjectMocks
    private JwtUtil jwtUtil;

    @Test
    public void testGenerateToken() {
        // Mock user details
        User user = User.builder().email("abc@gmail.com").password("12344566").role(Role.USER).build();
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().authority()));

        // Generate token
        String token = jwtUtil.generateToken(userDetails);

        // Assert
        assertNotNull(token);
    }

    @Test
    public void testExtractUsername() {
        // Mock token
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmNAZ21haWwuY29tIiwiZXhwIjoxNzE1MTY0MDU0LCJpYXQiOjE3MTUxNjM4MDR9.69TqkzV1qJmev77ZebYYm3AHhj4rwwaEOATq0aPhzUwKHc6aKCll7wjGkDOTZGul741-5zCKh06FPgiI_iPUrQ";

        // Extract username
        String username = jwtUtil.extractUsername(token);

        // Assert
        assertEquals("abc@gmail.com", username);
    }


    @Test
    public void testExtractExpiration() {
        // Mock token
        String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhYmNAZ21haWwuY29tIiwiZXhwIjoxNzE1MTY0MDU0LCJpYXQiOjE3MTUxNjM4MDR9.69TqkzV1qJmev77ZebYYm3AHhj4rwwaEOATq0aPhzUwKHc6aKCll7wjGkDOTZGul741-5zCKh06FPgiI_iPUrQ";

        // Extract expiration
        Date expiration = jwtUtil.extractExpiration(token);

        // Assert
        assertNotNull(expiration);
    }

    @Test
    public void testValidateToken() {
        // Mock generate token
        User user = User.builder().email("abc@gmail.com").password("1234456678").role(Role.USER).build();
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().authority()));
        String generateToken = jwtUtil.generateToken(userDetails);

        // Extract expiration
        boolean result = jwtUtil.validateToken(generateToken, userDetails);

        // Assert
        assertTrue(result);
    }
}