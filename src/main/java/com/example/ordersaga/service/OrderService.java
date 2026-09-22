package com.example.ordersaga.service;

import com.example.ordersaga.entity.Order;
import com.example.ordersaga.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    public Order saveOrder(Order order) {

        order.setStatus("CREATED");

        return orderRepository.save(order);
    }
}