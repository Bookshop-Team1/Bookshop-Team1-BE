package com.tw.bootcamp.bookshop.user.dto;

import com.tw.bootcamp.bookshop.user.address.CreateAddressRequest;
import lombok.*;

@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode
@NoArgsConstructor
public class CreateUserRequest {
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private String mobileNumber;
  private CreateAddressRequest address;

  public CreateUserRequest(String email, String password) {
    this.email = email;
    this.password = password;
  }
}
