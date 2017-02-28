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
    public void upsertFullName(String id, String newFullName) {
        Update update = new Update();
        mongoTemplate.upsert(Query.query(Criteria.where("id").is(id)),
                update.set("fullName", newFullName),
                Jedi.class);
    }
}
