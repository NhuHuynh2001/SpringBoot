package com.apidemo.springboot.repositories;

import com.apidemo.springboot.model.Category;
import com.apidemo.springboot.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category , Long> {
    List<Category> findByCategoryName(String categoryName);

//    Category findCategoryById(long id);



}
