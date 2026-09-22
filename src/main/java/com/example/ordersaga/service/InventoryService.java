package com.example.ordersaga.service;

import com.example.ordersaga.entity.Product;
import com.example.ordersaga.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InventoryService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public boolean reserveStock(Long productId, int quantity) {

        Product product = productRepository.findById(productId).orElse(null);

        if (product == null || product.getStock() < quantity) {
            return false;
        }

        product.setStock(product.getStock() - quantity);
        productRepository.save(product);

        return true;
    }

    @Transactional
    public void restoreStock(Long productId, int quantity) {

        Product product = productRepository.findById(productId).orElse(null);

        if (product != null) {
            product.setStock(product.getStock() + quantity);
            productRepository.save(product);
        }
    }
}