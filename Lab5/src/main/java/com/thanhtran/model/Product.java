package com.thanhtran.model;

import javax.persistence.*;

@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    public Product() {}
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public Long getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public double getPrice() {
        return this.price;
    }
}
