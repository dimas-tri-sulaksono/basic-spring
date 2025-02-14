package com.example.basic_spring.repository;

import com.example.basic_spring.model.Product;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Repository // buat nandain kalau ini repository

// KONEKSI KE DATABASE MENGUNAKAN JDBC
public class ProductRepository {

    // membuat koneksi ke database
    private final DataSource dataSource;

    // constructor
    public ProductRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    // method untuk mapping data ke object Product
    private Product mapToProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));                         // atur nilai id
        product.setProductName(resultSet.getString("product_name"));    // atur nilai productName
        product.setProductPrice(resultSet.getDouble("product_price"));  // .....
        product.setCategoryId(resultSet.getLong("categories_id"));      // .....

        return product;
    }

    // repo untuk get all data product
    // List<Product>        : simpan obejct dari model Product ke dalam array list [{...}]
    // throw SQLException   : untuk ngembaliin error jika operasi gagal
    public List<Product> findAll() throws SQLException{
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement(); // createStatement buat manggilquery
            ResultSet resultSet = statement.executeQuery("select * from products")) {

            List<Product> productList = new ArrayList<>(); // array kosong []

            // looping data yang kesimpan dari hasil query
            while (resultSet.next()){
                productList.add(mapToProduct(resultSet)); // productList []  (array kosong) + mapToProducts(resultSet) {} = [{...}]
            }

            return productList;
        }
    }

    // get data by id
    public Product findById(Long id) throws SQLException {
        try (Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("select * from products where id = ?"))
        {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) { // cek kalau ada datanya
               return mapToProduct(resultSet);
            }
            return null;
        }
    }

    // post new data
    public void save(Product product) throws SQLException{
        String query = "insert into products(product_name, product_price, categories_id) values(?, ?, ?)";

        try(Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)){
                preparedStatement.setString(1, product.getProductName());
                preparedStatement.setDouble(2, product.getProductPrice());
                preparedStatement.setLong(3, product.getCategoryId());
                preparedStatement.executeUpdate();
        }
    }

    // update data
    public void update(Product product) throws SQLException{
        String query = "update products set product_name = ?, product_price = ?, categories_id = ? where id = ?";

        try(Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setString(1, product.getProductName());
            preparedStatement.setDouble(2, product.getProductPrice());
            preparedStatement.setLong(3, product.getCategoryId());
            preparedStatement.setLong(4, product.getId());
            preparedStatement.executeUpdate();
        }
    }

    // delete data by id
    public void delete(Long id) throws SQLException{
        String query = "delete from products where id = ?";

        try(Connection connection = dataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)){
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        }
    }

}
