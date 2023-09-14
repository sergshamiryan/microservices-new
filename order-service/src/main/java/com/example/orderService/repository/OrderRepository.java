package com.example.orderService.repository;

import com.example.orderService.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Long> {

@Query(nativeQuery = true,value = "SELECT * FROM orders o WHERE o.id = :id")
    Order findOrderByIdCustom(Long id);
}
