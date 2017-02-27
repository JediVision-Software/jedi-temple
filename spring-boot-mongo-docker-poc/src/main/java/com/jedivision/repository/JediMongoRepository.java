package com.jedivision.repository;

public interface JediMongoRepository {

    void upsertFullname(String fullName, String newFullName);
    void upsertAge(String id, int age);
    void upsertAge(int age, int newAge);
}

