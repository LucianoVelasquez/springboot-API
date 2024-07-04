package com.AuthJWT.authJWT.services;

import com.AuthJWT.authJWT.entities.Product;
import com.AuthJWT.authJWT.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository repository;

    @Override
    public Product save(Product product) {
        return repository.save(product);
    }

    @Override
    public List<Product> findAll() {
        return (List<Product>) repository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return repository.findByName(name);
    }

    @Override
    public  Optional<Product> deleteById(Long id) {

        Optional<Product> newProduct = findById(id);

        repository.deleteById(id);

        return newProduct;

    }

    @Override
    public Optional<Product> update(Product product, Long id) {

        Optional<Product> productDb = findById(id);

        productDb.ifPresent(p -> {
            p.setName(product.getName());
            p.setType(product.getType());
            p.setPrice(product.getPrice());
            p.setStock(product.getStock());

        });

        repository.save(productDb.orElseThrow());

        return productDb;
    }
}
