package com.tw.bootcamp.bookshop.user;

import com.tw.bootcamp.bookshop.user.User.UserBuilder;
import com.tw.bootcamp.bookshop.user.address.Address;
import com.tw.bootcamp.bookshop.user.address.CreateAddressRequest;
import com.tw.bootcamp.bookshop.user.dto.CreateUserRequest;
import com.tw.bootcamp.bookshop.user.dto.JwtRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserTestBuilder {
  private final UserBuilder userBuilder;

  public UserTestBuilder() {

    userBuilder =
        User.builder()
            .email("testemail@test.com")
            .role(Role.USER)
            .password(User.PASSWORD_ENCODER.encode("foobar"))
            .firstName("Abhishek")
            .addresses(new ArrayList<>())
            .lastName("Gandhar")
            .mobileNumber("8282828282");
  }

  public static CreateUserRequest buildCreateUserRequest() {
    return new CreateUserRequest(
        "testemail@test.com",
        "foobar",
        "Abhishek",
        "Gandhar",
        "8282828282",
        CreateAddressRequest.builder().lineNoOne("line one").build());
  }

  public static JwtRequest buildAuthenticateUserRequest() {
    return new JwtRequest("testemail@test.com", "foobar");
  }

  public User build() {
    return userBuilder.build();
  }

  public UserTestBuilder withEmail(String email) {
    userBuilder.email(email);
    return this;
  }

  public UserTestBuilder withId(long id) {
    userBuilder.id(id);
    return this;
  }
}
