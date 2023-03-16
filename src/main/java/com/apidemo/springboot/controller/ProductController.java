package com.apidemo.springboot.controller;

import com.apidemo.springboot.model.Category;
import com.apidemo.springboot.model.Product;
import com.apidemo.springboot.model.ProductResponse;
import com.apidemo.springboot.model.ResponseObject;
import com.apidemo.springboot.repositories.CategoryRepository;
import com.apidemo.springboot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/Products")
public class ProductController {
    //DI = Dependency Injection
    @Autowired
    private ProductRepository repository;
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject>  findById(@PathVariable Long id){

        Optional<Product> foundProduct = repository.findById(id);
        if (foundProduct.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Query product success" , foundProduct)
            );
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("false","Can not find product with id:" +id , "")
            );
        }


    }

    @GetMapping("")
        //this request is: http://localhost:8080/api/v1/Products
    List<Product> getAllProducts() {
        return repository.findAll();
    }
    //insert new Product with POST method
    //Postman : Raw, JSON
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody Product newProduct) {
        //2 products must not have the same name !
        List<Product> foundProducts = repository.findByProductName(newProduct.getProductName().trim());
        if(foundProducts.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", "Product name already taken", "")
            );
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Insert Product successfully", repository.save(newProduct))
        );
    }
    //update, upsert = update if found, otherwise insert
    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> updateProduct(@RequestBody Product newProduct, @PathVariable Long id) {
        Product updatedProduct = repository.findById(id)
                .map(product -> {
                    product.setProductName(newProduct.getProductName());
                    product.setYear_s(newProduct.getYear_s());
                    product.setPrice(newProduct.getPrice());
                    return repository.save(product);
                }).orElseGet(() -> {
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "Update Product successfully", updatedProduct)
        );
    }
    //Delete a Product => DELETE method
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id) {
        boolean exists = repository.existsById(id);
        if(exists) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Delete product successfully", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("failed", "Cannot find product to delete", "")
        );
    }



}
