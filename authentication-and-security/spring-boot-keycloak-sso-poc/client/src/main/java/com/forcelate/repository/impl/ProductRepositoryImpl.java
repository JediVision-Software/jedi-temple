package com.forcelate.repository.impl;

import com.forcelate.model.Product;
import com.forcelate.repository.ProductRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class ProductRepositoryImpl implements ProductRepository {

    private List<Product> products = Arrays.asList(
            new Product(1, "Iphone 8"),
            new Product(2, "Iphone 7"),
            new Product(3, "Iphone 6")
    );

    public List<Product> findAll() {
        return this.products;
    }
}
