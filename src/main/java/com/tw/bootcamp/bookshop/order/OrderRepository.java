package com.tw.bootcamp.bookshop.order;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Order save(Order orderToSave);
}
