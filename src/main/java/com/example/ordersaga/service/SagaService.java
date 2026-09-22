package com.example.ordersaga.service;

import com.example.ordersaga.entity.Order;
import com.example.ordersaga.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SagaService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private PaymentService paymentService;

    public Order placeOrder(Order order) {

        // Step 1: Create Order
        order.setStatus("CREATED");
        order = orderRepository.save(order);

        // Step 2: Make Payment
        boolean paymentSuccess = paymentService.makePayment(order.getAmount());

        if (!paymentSuccess) {
            order.setStatus("CANCELLED");
            return orderRepository.save(order);
        }

        // Step 3: Reserve Inventory
        boolean inventoryReserved =
                inventoryService.reserveStock(
                        order.getProductId(),
                        order.getQuantity()
                );

        // Step 4: Compensation if inventory fails
        if (!inventoryReserved) {

            paymentService.refund(order.getAmount());

            order.setStatus("CANCELLED");

            return orderRepository.save(order);
        }

        // Step 5: Everything successful
        order.setStatus("CONFIRMED");

        return orderRepository.save(order);
    }
}