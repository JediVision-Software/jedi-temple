package com.jedivision.test.runner;

import com.jedivision.entity.Equipment;
import com.jedivision.entity.Jedi;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.util.Properties;

@Slf4j
public abstract class ApplicationDaoRunner {
    private SessionFactory sessionFactory;

    public ApplicationDaoRunner() {
        Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("/hibernate-test.properties"));
        } catch (IOException e) {
            log.error("Configuration file not found {}", e.getMessage());
        }
        Configuration config = new Configuration();
        for (String property: properties.stringPropertyNames()) {
            config.setProperty(property, properties.getProperty(property));
        }
        config.addAnnotatedClass(Jedi.class);
        config.addAnnotatedClass(Equipment.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(config.getProperties())
                .build();
        sessionFactory = config.buildSessionFactory(serviceRegistry);
    }

    @Before
    public void before() {
        getSession().beginTransaction();
    }

    @After
    public void after() {
        getSession().close();
    }

    public Session getSession()
    {
        try {
            return sessionFactory.getCurrentSession();
        } catch (SessionException se) {
            return sessionFactory.openSession();
        }
    }
}
