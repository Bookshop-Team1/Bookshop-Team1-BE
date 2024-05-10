package com.tw.bootcamp.bookshop.user.address;

import com.tw.bootcamp.bookshop.user.User;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
public class AddressTestBuilder {
    private final Address.AddressBuilder addressBuilder;

    public AddressTestBuilder() {
        this.addressBuilder = Address.builder()
                .lineNoOne("4 Privet Drive")
                .lineNoTwo("Little Whinging")
                .city("Godstone")
                .pinCode("A22 001")
                .country("Surrey");
    }

    public Address build() {
        return addressBuilder.build();
    }

    public Address buildWith(User user) {
        return addressBuilder.user(user).build();
    }
}
