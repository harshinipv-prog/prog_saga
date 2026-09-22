package com.example.ordersaga.repository;

import com.example.ordersaga.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}