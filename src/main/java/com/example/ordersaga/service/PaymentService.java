package com.example.ordersaga.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public boolean makePayment(double amount) {

        System.out.println("Payment successful: " + amount);

        return true;
    }

    public void refund(double amount) {

        System.out.println("Payment refunded: " + amount);
    }
}