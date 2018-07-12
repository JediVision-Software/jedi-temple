package com.forcelate.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forcelate.model.Product;
import com.forcelate.service.ProductService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Product> findAll() {
        return this.getProductsFromRestApi();
    }

    private List<Product> getProductsFromRestApi() {
        List<Product> productList = new ArrayList<>();
        try {
            HttpResponse<String> response = Unirest.get("http://localhost:8082/api/products")
                    .asString();
            productList.addAll(this.objectMapper.readValue(response.getRawBody(), new TypeReference<List<Product>>() {}));
        } catch (UnirestException e) {
            LOGGER.error("Cannot get data from response ", e);
        } catch (IOException e) {
            LOGGER.error("Cannot parse response data", e);
        }
        return productList;
    }
}
