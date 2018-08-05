package com.forcelate.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forcelate.model.Product;
import com.forcelate.service.ProductService;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.keycloak.KeycloakSecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private static final String HEADER_AUTHORIZATION_KEY = "Authorization";
    private static final String HEADER_AUTHORIZATION_VALUE = "BEARER ";
    private static final String RESOURCE_URL = "http://localhost:8082/api/products";
    private final KeycloakSecurityContext keycloakSecurityContext;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    public ProductServiceImpl(KeycloakSecurityContext keycloakSecurityContext) {
        this.keycloakSecurityContext = keycloakSecurityContext;
    }

    @Override
    public List<Product> findAll() {
        return this.getProductsFromRestApi();
    }

    private List<Product> getProductsFromRestApi() {
        List<Product> productList = new ArrayList<>();
        try {
            HttpResponse<JsonNode> response = Unirest.get(RESOURCE_URL)
                    .headers(this.getHeaders())
                    .asJson();
            if (response.getStatus() == HttpStatus.SC_OK) {
                productList.addAll(this.objectMapper.readValue(response.getRawBody(), new TypeReference<List<Product>>() {
                }));
            }
        } catch (UnirestException e) {
            LOGGER.error("Cannot get data from response ", e);
        } catch (IOException e) {
            LOGGER.error("Cannot parse response data", e);
        }
        return productList;
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.getMimeType());
        headers.put(HEADER_AUTHORIZATION_KEY, this.getAccessToken());
        return headers;
    }

    private String getAccessToken() {
        return HEADER_AUTHORIZATION_VALUE + this.keycloakSecurityContext.getTokenString();
    }
}
