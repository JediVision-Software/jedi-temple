package com.jedivision.repository;

import com.jedivision.entity.Jedi;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JediRepository extends MongoRepository<Jedi, String> {
}
