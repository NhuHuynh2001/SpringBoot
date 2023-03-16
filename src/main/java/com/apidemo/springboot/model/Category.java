package com.apidemo.springboot.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "category_product")
//@Data
public class Category {



//    @OneToMany(mappedBy = "category")

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String categoryName;
    private String describe;

    private int product_Id;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category() {
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Category(Long id, String categoryName, String describe, int product_Id) {
        this.id = id;
        this.categoryName = categoryName;
        this.describe = describe;
        this.product_Id = product_Id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(int product_Id) {
        this.product_Id = product_Id;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", describe='" + describe + '\'' +
                ", product_Id=" + product_Id +
                '}';
    }
}
