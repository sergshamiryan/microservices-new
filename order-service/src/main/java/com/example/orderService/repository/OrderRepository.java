package com.example.orderService.repository;

import com.example.orderService.model.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {


    @Query("select o From Order o  WHERE o.id = 2")
    Order findById2(long id);
}
