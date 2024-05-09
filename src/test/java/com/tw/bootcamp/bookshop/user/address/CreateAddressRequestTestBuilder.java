package com.tw.bootcamp.bookshop.user.address;

public class CreateAddressRequestTestBuilder {
    private final CreateAddressRequest.CreateAddressRequestBuilder addressRequestBuilder;

    public CreateAddressRequestTestBuilder() {
        this.addressRequestBuilder = CreateAddressRequest.builder()
                .lineNoOne("4 Privet Drive")
                .lineNoTwo("Little Whinging")
                .city("Godstone")
                .pinCode("A22 001")
                .country("Surrey");
    }

    public CreateAddressRequest build() {
        return addressRequestBuilder.build();
    }
}
