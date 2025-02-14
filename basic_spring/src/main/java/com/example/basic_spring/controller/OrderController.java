package com.example.basic_spring.controller;

import com.example.basic_spring.model.Order;
import com.example.basic_spring.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/orders")

public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public List<Order> getAllOrder() throws SQLException{
        return orderService.getAllOrder();
    }

    @GetMapping("/{id}") // get data by id
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) throws SQLException {
        Order order = orderService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping // add new product
    public ResponseEntity<Order> addOrder(@RequestBody Order order) throws SQLException {
        orderService.addOrder(order);
        return ResponseEntity.ok(order);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) throws SQLException {
        order.setId(id);
        orderService.updateOrder(order);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) throws SQLException {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    } 

}
