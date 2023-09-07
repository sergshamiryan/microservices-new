package com.example.productservice.repository;

import com.example.productservicemicroservice.model.Products;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Products,String> {



}
