package com.example.basic_spring.repository;

import com.example.basic_spring.model.Order;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.sql.Date;
import java.util.*;

@Repository

public class OrderRepository {
    private final DataSource dataSource;

    public OrderRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    private Order mapToOrder(ResultSet resultSet) throws SQLException{
        Order order = new Order();
        order.setId(resultSet.getLong("id"));
        order.setCustomerId(resultSet.getLong("customer_id"));
        order.setOrderDate(resultSet.getDate("order_date"));
        return order;
    }

    public List<Order> findAll() throws SQLException{
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from orders")){
            List<Order> orderList = new ArrayList<>();
            while (resultSet.next()){
                orderList.add(mapToOrder(resultSet));
            }
            return orderList;
        }
    }

    public Order findById(Long id) throws SQLException{
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from orders where id = ?")){
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return mapToOrder(resultSet);
            }
            return null;
        }
    }

    public void save(Order order) throws SQLException{
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("insert into orders(customer_id, order_date) values (?, ?)")){
            preparedStatement.setLong(1, order.getCustomerId());
            preparedStatement.setDate(2, new Date(order.getOrderDate().getTime()));
            preparedStatement.executeUpdate();
        }
    }

    public void update(Order order) throws SQLException{
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("update orders set customer_id = ?, order_date = ? where id = ?")){
            preparedStatement.setLong(1, order.getCustomerId());
            preparedStatement.setDate(2, new Date(order.getOrderDate().getTime()));
            preparedStatement.setLong(3, order.getId());
            preparedStatement.executeUpdate();
        }
    }

    public void delete(Long id) throws SQLException{
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("delete from orders where id = ?")){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }
}
