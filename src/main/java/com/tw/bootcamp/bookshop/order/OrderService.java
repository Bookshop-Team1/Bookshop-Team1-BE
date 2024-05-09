package com.tw.bootcamp.bookshop.order;

import com.tw.bootcamp.bookshop.book.*;
import com.tw.bootcamp.bookshop.user.User;
import com.tw.bootcamp.bookshop.user.address.Address;
import com.tw.bootcamp.bookshop.user.address.AddressRepository;
import com.tw.bootcamp.bookshop.user.address.CreateAddressRequest;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final AddressRepository addressRepository;

    public OrderService(OrderRepository orderRepository, AddressRepository addressRepository) {
        this.orderRepository = orderRepository;
        this.addressRepository = addressRepository;
    }

    public Order createOrder(Book item, User user, CreateAddressRequest shippingAddressRequest) {
        Address addressToSave = Address.create(shippingAddressRequest, user);
        Address savedAddress = addressRepository.save(addressToSave);

        Order createdOrder = Order.builder()
                .book(item)
                .user(user)
                .shippingAddress(savedAddress)
                .itemCount(1)
                .shippingCost(50.00)
                .currency("INR")
                .paymentMethod(PaymentMethod.CASH_ON_DELIVERY.toString())
                .build();
        return orderRepository.save(createdOrder);
    }
}
