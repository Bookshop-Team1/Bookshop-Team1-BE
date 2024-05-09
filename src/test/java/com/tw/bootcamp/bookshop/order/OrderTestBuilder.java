package com.tw.bootcamp.bookshop.order;

import com.tw.bootcamp.bookshop.book.BookTestBuilder;
import com.tw.bootcamp.bookshop.user.User;
import com.tw.bootcamp.bookshop.user.address.Address;

public class OrderTestBuilder {
    private final Order.OrderBuilder orderBuilder;

    public OrderTestBuilder() {
        this.orderBuilder = Order.builder()
                .itemCount(1)
                .shippingCost(50.00)
                .currency("INR")
                .paymentMethod(PaymentMethod.CASH_ON_DELIVERY);
    }

    public Order build() {
        return orderBuilder.build();
    }

    public Order.OrderBuilder orderBuilder() {
        return orderBuilder;
    }
}
