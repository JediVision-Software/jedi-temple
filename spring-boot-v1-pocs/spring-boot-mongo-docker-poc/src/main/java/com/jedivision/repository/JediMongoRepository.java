package com.jedivision.repository;

public interface JediMongoRepository {
    void upsertFullName(String id, String newFullName);
}

