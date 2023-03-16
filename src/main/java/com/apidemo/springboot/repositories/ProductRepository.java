package com.apidemo.springboot.repositories;

import com.apidemo.springboot.model.Category;
import com.apidemo.springboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductName(String productName);

//    Product findProductByProductName(String productName);

//    List<Product> findProductById(Long id);

}
