package com.example.ordersaga.controller;

import com.example.ordersaga.entity.Order;
import com.example.ordersaga.service.SagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private SagaService sagaService;

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return sagaService.placeOrder(order);
    }
}