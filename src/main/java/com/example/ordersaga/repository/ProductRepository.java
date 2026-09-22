package com.example.ordersaga.repository;

import com.example.ordersaga.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}