package com.jedivision;

import com.jedivision.configuration.DropwizardConfiguration;
import com.jedivision.dao.EquipmentDao;
import com.jedivision.dao.JediDao;
import com.jedivision.entity.Equipment;
import com.jedivision.entity.Jedi;
import com.jedivision.healthcheck.DatabaseHealthCheck;
import com.jedivision.resource.EquipmentResource;
import com.jedivision.resource.IndexResource;
import com.jedivision.resource.JediResource;
import com.jedivision.service.EquipmentService;
import com.jedivision.service.JediService;
import com.jedivision.service.impl.EquipmentServiceImpl;
import com.jedivision.service.impl.JediServiceImpl;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Application extends io.dropwizard.Application<DropwizardConfiguration> {

    private final HibernateBundle<DropwizardConfiguration> hibernateBundle =
            new HibernateBundle<DropwizardConfiguration>(
                    Equipment.class,
                    Jedi.class
            ) {
        @Override
        public DataSourceFactory getDataSourceFactory(DropwizardConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Exception {
        new Application().run(args);
    }

    @Override
    public void initialize( Bootstrap<DropwizardConfiguration> bootstrap) {
        bootstrap.addBundle(hibernateBundle);
    }

    @Override
    public void run(DropwizardConfiguration configuration, Environment environment) throws ClassNotFoundException {
        // DAO
        final JediDao jediDao = new JediDao(hibernateBundle.getSessionFactory());
        final EquipmentDao equipmentDao = new EquipmentDao(hibernateBundle.getSessionFactory());
        // Service
        final JediService jediService = new JediServiceImpl(jediDao, equipmentDao);
        final EquipmentService equipmentService = new EquipmentServiceImpl(equipmentDao);
        // Health Check
        environment.healthChecks().register("database", new DatabaseHealthCheck(configuration));
        // Resources
        environment.jersey().register(new JediResource(jediService));
        environment.jersey().register(new EquipmentResource(equipmentService));
        environment.jersey().register(new IndexResource(jediService));
    }
}
