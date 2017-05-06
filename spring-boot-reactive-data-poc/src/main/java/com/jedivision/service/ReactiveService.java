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
        LOGGER.info("Reactive PoC. Insert Operation via ReactiveMongoOperations");
        Flux<Jedi> jediFlux = Flux.just(
                Jedi.builder().name("Obi-Wan Kenobi").age(50).build(),
                Jedi.builder().name("Yoda").age(950).build()
        );
        reactiveMongoOperations.insertAll(jediFlux.collectList());

        Mono<Long> count = jediRepository.count();
        LOGGER.info("count: " + count.block());
    }
}
