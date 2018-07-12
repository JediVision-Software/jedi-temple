package com.forcelate.service.impl;

import com.forcelate.model.Product;
import com.forcelate.repository.ProductRepository;
import com.forcelate.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }
}
