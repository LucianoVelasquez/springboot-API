package com.AuthJWT.authJWT.services;

import com.AuthJWT.authJWT.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    Product save(Product product);

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    Optional<Product> deleteById(Long id);

    Optional<Product> update(Product product,Long id);
}
