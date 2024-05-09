package com.tw.bootcamp.bookshop.order;

import com.tw.bootcamp.bookshop.book.*;
import com.tw.bootcamp.bookshop.user.User;
import com.tw.bootcamp.bookshop.user.UserRepository;
import com.tw.bootcamp.bookshop.user.UserTestBuilder;
import com.tw.bootcamp.bookshop.user.address.*;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
class OrderServiceTest {
    private OrderService orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private AddressRepository addressRepository;

    @BeforeEach
    public void setUp() {
        orderService = new OrderService(orderRepository, addressRepository);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        addressRepository.deleteAll();
        orderRepository.deleteAll();
    }

    @Test
    void shouldCreateOrder() {
        Book book = new BookTestBuilder().build();
        User user = new UserTestBuilder().build();
        Address address = new AddressTestBuilder().buildWith(user);
        CreateAddressRequest addressRequest = new CreateAddressRequestTestBuilder().build();
        Order.OrderBuilder orderBuilder = new OrderTestBuilder().orderBuilder();
        Order order = orderBuilder
                .user(user)
                .book(book)
                .shippingAddress(address)
                .build();
        when(addressRepository.save(any(Address.class))).thenReturn(address);
        when(userRepository.save(any(User.class))).thenReturn(user);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        Order actualOrder = orderService.createOrder(book, user, addressRequest);

        Assertions.assertThat(actualOrder).isEqualTo(order);
        verify(orderRepository, times(1)).save(any());
        verify(addressRepository, times(1)).save(any());
    }

}