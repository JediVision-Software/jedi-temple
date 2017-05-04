package com.jedivision.repository;

import com.jedivision.domain.Jedi;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface JediRepository extends ReactiveCrudRepository<Jedi, String> {
    Flux<Jedi> findByName(String name);
}
