package com.tw.bootcamp.bookshop.user;

import com.tw.bootcamp.bookshop.user.dto.CreateUserRequest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
public class CreateUserRequestTestBuilder {
  private CreateUserRequest.CreateUserRequestBuilder requestBuilder;

  public CreateUserRequestTestBuilder() {
    requestBuilder =
        CreateUserRequest.builder()
            .email("testemail@test.com")
            .password("foobar")
            .firstName("Abhishek")
            .lastName("Gandhar")
            .mobileNumber("898989899");
  }

  CreateUserRequest build() {
    return requestBuilder.build();
  }

  public CreateUserRequestTestBuilder withEmptyEmail() {
    requestBuilder.email("");
    return this;
  }

  public CreateUserRequestTestBuilder withEmptyPassword() {
    requestBuilder.password("");
    return this;
  }
}
