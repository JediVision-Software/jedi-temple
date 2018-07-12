package com.forcelate.repository;

import com.forcelate.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
}
