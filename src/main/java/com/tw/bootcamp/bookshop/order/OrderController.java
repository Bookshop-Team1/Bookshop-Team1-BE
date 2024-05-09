package com.tw.bootcamp.bookshop.order;

import com.tw.bootcamp.bookshop.book.Book;
import com.tw.bootcamp.bookshop.book.BookService;
import com.tw.bootcamp.bookshop.user.User;
import com.tw.bootcamp.bookshop.user.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;
    private final BookService bookService;


    public OrderController(OrderService orderService, UserService userService, BookService bookService) {
        this.orderService = orderService;
        this.userService = userService;
        this.bookService = bookService;
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrderHandler(@RequestBody OrderRequest orderRequest) throws Exception {
        User user = userService.getByEmail(orderRequest.getUserEmail());
        Book book = bookService.getById(orderRequest.getBookId());

        Order order = orderService.createOrder(book, user, orderRequest.getAddressRequest());

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

}
