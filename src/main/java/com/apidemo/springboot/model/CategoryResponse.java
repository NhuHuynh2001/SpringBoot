package com.apidemo.springboot.model;

import java.util.List;

public class CategoryResponse {

    private Long id;
    private String categoryName;
    private String describe;

    private List<Product> product;




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

    public List<Product> getProduct() {
        return product;
    }

    public void setProduct(List<Product> product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "CategoryResponse{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", describe='" + describe + '\'' +
                ", product=" + product +
                '}';
    }
}
