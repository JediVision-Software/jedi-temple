package com.jedivision.configuration;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.mapping.event.LoggingEventListener;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

//@Configuration
//@EnableReactiveMongoRepositories
//@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)
public class ApplicationReactiveConfiguration extends AbstractReactiveMongoConfiguration {

    @Override
    @Bean
    @DependsOn("embeddedMongoServer")
    public MongoClient mongoClient() {
        return MongoClients.create("mongodb://localhost:27017");
    }

    @Override
    protected String getDatabaseName() {
        return "reactive-jedis";
    }

    @Bean
    public LoggingEventListener mongoEventListener() {
        return new LoggingEventListener();
    }

}
