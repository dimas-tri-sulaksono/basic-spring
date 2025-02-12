package com.example.basic_spring.controller;

import com.example.basic_spring.model.Product;
import com.example.basic_spring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

// controller untuk routing

@RestController // anotasi buat web service yang handle permintaan dan response HTTP (Get, Post, Put, Delete)
@RequestMapping("api/products") // anotasi buat nentuin endpoint dari contoller ini
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping // anotasi untuk request GET
    public List<Product> getAllProducts() throws SQLException{
        return productService.getAllProduct();
    }

    @GetMapping("/{id}") // get data by id
    public ResponseEntity<Product> getProductById(@PathVariable Long id) throws SQLException {
        Product product = productService.findById(id);
            return ResponseEntity.ok(product);
//        if (product != null) {
//        } else {
//            return ResponseEntity.notFound().build();
//        }
    }

    @PostMapping // add new product
    public ResponseEntity<Product> addProduct(@RequestBody Product product) throws SQLException {
        productService.addProduct(product);
        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) throws SQLException {
        product.setId(id);
        productService.updateProduct(product);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) throws SQLException {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}