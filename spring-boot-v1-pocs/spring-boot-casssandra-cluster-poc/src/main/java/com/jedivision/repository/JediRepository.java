package com.jedivision.repository;

import com.jedivision.entity.Jedi;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JediRepository extends CassandraRepository<Jedi> {
}
