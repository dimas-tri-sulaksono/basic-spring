package com.example.basic_spring.service;

import com.example.basic_spring.model.Product;
import com.example.basic_spring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service // anotasi untuk nyediain logika bisnis seperti CRUD (manipulasi data)
public class ProductService {

    @Autowired // untuk inject/ngehubungin service ke repository
    private ProductRepository productRepository;

    // login untuk get all data
    public List<Product> getAllProduct() throws SQLException {
        return productRepository.findAll();
    }

    // find by id
    public Product findById(Long id) throws SQLException {
        return productRepository.findById(id);
    }

    // add data
    public void addProduct(Product product) throws SQLException {
        productRepository.save(product);
    }

    // update data
    public void updateProduct(Product product) throws SQLException {
        productRepository.update(product);
    }

    // delete data
    public void deleteProduct(Long id) throws SQLException {
        productRepository.delete(id);
    }
}
