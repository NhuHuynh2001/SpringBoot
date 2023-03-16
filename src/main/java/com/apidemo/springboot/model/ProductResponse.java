package com.apidemo.springboot.model;

import lombok.Data;

//@Data
public class ProductResponse {
    private Long id;
    private String productName;
    private int year_s;
    private Double price;
    private String url;
    private Category category;

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

//    public void mapper(Product product){
//        this.id = product.getId();
//        this.productName = product.getProductName();
//        this.year_s = product.getYear_s();
//        this.price = product.getPrice();
//        this.url = product.getUrl();
//    }

}
