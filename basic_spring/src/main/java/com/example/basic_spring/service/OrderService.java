package com.example.basic_spring.service;

import com.example.basic_spring.model.Order;
import com.example.basic_spring.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service

public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> getAllOrder() throws SQLException {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) throws SQLException {
        return orderRepository.findById(id);
    }

    public void addOrder(Order order) throws SQLException {
        orderRepository.save(order);
    }

    public void updateOrder(Order order) throws SQLException {
        orderRepository.update(order);
    }

    public void deleteOrder(Long id) throws SQLException {
        orderRepository.delete(id);
    }
}
