package com.jedivision.repository;

import com.jedivision.domain.Jedi;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface JediRepository extends ReactiveCrudRepository<Jedi, String> {
    Flux<Jedi> findByName(String name);

    Flux<Jedi> findByAge(Integer age);

    @Query("{ 'name': ?0, 'age': ?1}")
    Mono<Jedi> findByNameAndAge(String name, Integer age);

}
