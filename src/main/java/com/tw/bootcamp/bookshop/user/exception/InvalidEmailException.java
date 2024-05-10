package com.tw.bootcamp.bookshop.user.exception;

public class InvalidEmailException extends Exception {
    public InvalidEmailException() {
        super("User with same email already created");
    }
}
