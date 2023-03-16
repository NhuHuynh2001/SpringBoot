package com.apidemo.springboot.controller;

import com.apidemo.springboot.model.Category;
import com.apidemo.springboot.model.CategoryResponse;
import com.apidemo.springboot.model.Product;
import com.apidemo.springboot.model.ResponseObject;
import com.apidemo.springboot.repositories.CategoryRepository;
import com.apidemo.springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/Category")
public class CategoryController {

    @Autowired
    private CategoryRepository repository;

    @Autowired
    private  ProductRepository productRepository;

    @GetMapping("")
    List<Category> getAllCategory() {
//        public String getListProductByName(){}
        return repository.findAll();


//        return List.of(new Category("Iphone 11prm", "Điện thoại"));
    }

    @GetMapping("/{id}")
        //Let's return an object with: data, message, status
    Category findById(@PathVariable Long id) {
        Optional<Category> foundCategory = repository.findById(id);
//        List<Product> product =  productRepository.findProductById(foundCategory.get().getId());
//        CategoryResponse categoryResponse = new CategoryResponse();
//        categoryResponse.setCategoryName(foundCategory.get().getCategoryName());
//        categoryResponse.setId(foundCategory.get().getId());
//        categoryResponse.setDescribe(foundCategory.get().getDescribe());
//        categoryResponse.setProduct(product);

        return foundCategory.get();
    }

    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertCategory(@RequestBody Category newCategory) {

        List<Category> foundCategory = repository.findByCategoryName(newCategory.getCategoryName().trim());
        if(foundCategory.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Category name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Category successfully", repository.save(newCategory))
        );
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateCategory(@RequestBody Category newCategory, @PathVariable Long id) {


        Category updatedCategory = repository.findById(id)
                .map(category -> {
                category.setCategoryName(newCategory.getCategoryName());
                category.setDescribe(newCategory.getDescribe());
                return repository.save(category);
                }).orElseGet(() -> {
                newCategory.setId(id);
                return repository.save(newCategory);
                        });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update Category successfully", updatedCategory)
        );
    }
    //Delete a Product => DELETE method
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        boolean exists = repository.existsById(id);
        if(exists) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete Category successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find Category to delete", "")
        );
    }


}