package com.jedivision.repository.impl;

import com.jedivision.entity.Jedi;
import com.jedivision.repository.JediMongoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class JediMongoRepositoryImpl implements JediMongoRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void upsertFullname(String fullname, String newFullName) {
        Update update = new Update();
        mongoTemplate.upsert(Query.query(Criteria.where("fullName").is(fullname)),
                update.set("fullName", newFullName),
                Jedi.class);
    }

    @Override
    public void upsertAge(String id, int age) {
        Update update = new Update();
        mongoTemplate.upsert(Query.query(Criteria.where("id").is(id)),
                update.set("age", age),
                Jedi.class);
    }

    @Override
    public void upsertAge(int age, int newAge) {
        Update update = new Update();
        mongoTemplate.upsert(Query.query(Criteria.where("age").is(age)),
                update.set("age", newAge),
                Jedi.class);
    }
}
