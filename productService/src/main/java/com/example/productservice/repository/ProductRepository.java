package com.example.productservice.repository;


import com.example.productservice.model.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Products,String> {



}
