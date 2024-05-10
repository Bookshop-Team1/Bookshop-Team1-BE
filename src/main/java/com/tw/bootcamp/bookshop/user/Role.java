package com.tw.bootcamp.bookshop.user;

public enum Role {
    USER;

    public String authority() {
        return "ROLE_" + this.name();
    }
}
