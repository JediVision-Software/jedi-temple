package com.jedivision;

import com.jedivision.service.ReactiveService;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@SpringBootApplication(exclude = { MongoAutoConfiguration.class, MongoDataAutoConfiguration.class })
@EnableReactiveMongoRepositories
@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
@RequiredArgsConstructor
public class Application extends AbstractReactiveMongoConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    private final Environment environment;

    @Override
    @Bean
    @DependsOn("embeddedMongoServer")
    public MongoClient mongoClient() {
        int port = environment.getProperty("local.mongo.port", Integer.class);
        return MongoClients.create(String.format("mongodb://localhost:%d", port));
    }

    @Override
    protected String getDatabaseName() {
        return "reactive-jedis";
    }

    @Bean
    public LoggingEventListener mongoEventListener() {
        return new LoggingEventListener();
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Application.class, args);
        LOGGER.info("SpringBoot ReactiveData PoC application started...");
        ReactiveService reactiveService = applicationContext.getBean(ReactiveService.class);
        reactiveService.demo();
    }
}
