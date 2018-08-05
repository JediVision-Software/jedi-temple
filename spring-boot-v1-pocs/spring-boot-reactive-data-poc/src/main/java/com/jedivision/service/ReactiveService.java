package com.jedivision.service;

import com.jedivision.domain.Jedi;
import com.jedivision.repository.JediRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Service
public class ReactiveService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReactiveService.class);

    // Repository
    private final JediRepository jediRepository;
    // Operation
    private final ReactiveMongoOperations reactiveMongoOperations;

    @Autowired
    public ReactiveService(JediRepository jediRepository,
                           ReactiveMongoOperations reactiveMongoOperations) {
        this.jediRepository = jediRepository;
        this.reactiveMongoOperations = reactiveMongoOperations;

    }

    public void demo() {
        LOGGER.info("Reactive PoC. Insert Operation via @Repository");
        jediRepository.save(Jedi.builder().name("Qui-Gon Jinn").age(50).build()).block();
        // expected count === 1 element
        Mono<Long> count = jediRepository.count();
        LOGGER.info("Jedi count: " + count.block());

        LOGGER.info("Reactive PoC. Insert Operation via ReactiveMongoOperations");
        Flux<Jedi> jediFluxInsertAll = Flux.just(
                Jedi.builder().name("Obi-Wan Kenobi").age(50).build(),
                Jedi.builder().name("Yoda").age(950).build()
        );
        reactiveMongoOperations.insertAll(jediFluxInsertAll.collectList()).subscribe();
        // expected count === 3 element
        count = jediRepository.count();
        LOGGER.info("Jedi count: " + count.block());
    }
}
