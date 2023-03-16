package com.apidemo.springboot.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Objects;

@Entity
@Table
public class Product {
    //this is "primary key"

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //auto-increment

    private Long id;
    private String productName;
    private int year_s;
    private Double price;
    private String url;

    @ManyToOne
    private Category category;



    public void setAge(int age) {
        this.age = age;
    }



    public Product() {
    }


    private int age;//age is calculated from "year"

    public int getAge() {
        return Calendar.getInstance().get(Calendar.YEAR) - year_s;
    }

    public Product(Long id, String productName, int year_s, Double price, String url, int categoryId) {
        this.id = id;
        this.productName = productName;
        this.year_s = year_s;
        this.price = price;
        this.url = url;
//        this.categoryId = categoryId;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getYear_s() {
        return year_s;
    }

    public void setYear_s(int year_s) {
        this.year_s = year_s;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }



    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", year_s=" + year_s +
                ", price=" + price +
                ", url='" + url + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return year_s == product.year_s && age == product.age
                && Objects.equals(id, product.id) && Objects.equals(productName, product.productName)
                && Objects.equals(price, product.price) && Objects.equals(url, product.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, productName, year_s, price, url, age);
    }
}
