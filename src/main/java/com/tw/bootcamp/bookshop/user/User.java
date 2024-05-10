package com.tw.bootcamp.bookshop.user;

import com.tw.bootcamp.bookshop.user.address.Address;
import com.tw.bootcamp.bookshop.user.address.CreateAddressRequest;
import com.tw.bootcamp.bookshop.user.dto.CreateUserRequest;
import com.tw.bootcamp.bookshop.user.dto.UpdateUserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    public static final PasswordEncoder PASSWORD_ENCODER = new BCryptPasswordEncoder();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
    @NotBlank(message = "Password is mandatory")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    public User() {
    }

    private User(String email, String password, Role role, String firstName, String lastName, String mobileNumber, List<Address> addresses) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.addresses = addresses;
    }

    public static User create(CreateUserRequest userRequest) {
        String password = "";
        if (!userRequest.getPassword().isEmpty()) {
            password = PASSWORD_ENCODER.encode(userRequest.getPassword());
        }
        User newUser = new User(userRequest.getEmail(), password,
                Role.USER, userRequest.getFirstName(), userRequest.getLastName(), userRequest.getMobileNumber(), null);
        List<Address> addressList = new ArrayList<>();
        CreateAddressRequest address = userRequest.getAddress();
        if (address != null) {
            Address newAddress = Address.create(address, newUser);
            addressList.add(newAddress);
        }
        newUser.addresses = addressList;
        return newUser;
    }

    public void update(UpdateUserRequest updateUserRequest) {
        firstName = updateUserRequest.getFirstName();
        lastName = updateUserRequest.getLastName();
        mobileNumber = updateUserRequest.getMobileNumber();
        if (updateUserRequest.getAddress() != null) {
            Address address = Address.create(updateUserRequest.getAddress(), this);
            addresses.add(address);
        }
    }
}
