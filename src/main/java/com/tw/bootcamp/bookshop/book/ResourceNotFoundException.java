package com.tw.bootcamp.bookshop.book;

public class ResourceNotFoundException extends RuntimeException {
  ResourceNotFoundException(String message) {
    super(message);
  }
}
