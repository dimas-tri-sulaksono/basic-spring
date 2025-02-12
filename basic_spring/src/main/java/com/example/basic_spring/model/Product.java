package com.example.basic_spring.model;

import jakarta.persistence.*;

@Entity // anotasi untuk mapping table dari database
@Table(name = "products") // akses table di database

public class Product {
    @Id // untuk nandain bahwa kolom ini adalah primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY) // untuk generate id increment otomatis
    @Column(name = "id") // ambil kolom di database
    private Long id;

    // nullable : nandain apakah data kolom tersebut boleh kosong/tidak
    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price", nullable = false)
    private double productPrice;

    @Column(name ="categories_id")
    private Long categoryId;

    // getter & setter
    // getter : buat ambil/akses data
    // setter : buat set (add/update) data
    public Long getId(){return id;}
    public void setId(Long id){this.id = id;}

    public String getProductName(){return productName;}
    public void setProductName(String productName){this.productName = productName;}

    public double getProductPrice(){return productPrice;}
    public void setProductPrice(double productPrice){this.productPrice = productPrice;}

    public Long getCategoryId(){return categoryId;}
    public void setCategoryId(Long categoryId){this.categoryId = categoryId;}

}
