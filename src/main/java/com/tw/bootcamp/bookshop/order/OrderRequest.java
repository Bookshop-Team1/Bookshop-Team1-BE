package com.tw.bootcamp.bookshop.order;

import com.tw.bootcamp.bookshop.user.address.CreateAddressRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class OrderRequest {
    private long bookId;
    private String userEmail;
    private CreateAddressRequest addressRequest;
}
