package com.AuthJWT.authJWT.repositories;

import com.AuthJWT.authJWT.entities.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product,Long> {
    Optional<Product> findByName(String name);
}
