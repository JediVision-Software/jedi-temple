package com.jedivision.healthcheck;

import com.codahale.metrics.health.HealthCheck;
import com.jedivision.Application;
import com.jedivision.configuration.DropwizardConfiguration;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import static io.dropwizard.testing.ResourceHelpers.resourceFilePath;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DatabaseHealthCheckTest {
    private static final String CONFIG_PATH = resourceFilePath("configuration-test.yml");
    private static final String HELTHCHECK_MESSAGE = "Cannot connect to jdbc:h2:mem:test;INIT=runscript from 'classpath:data.sql'";

    @ClassRule
    public static final DropwizardAppRule<DropwizardConfiguration> RULE =
            new DropwizardAppRule<>(Application.class, CONFIG_PATH);

    @Test
    public void checkFalse() throws Exception {
        // Arrange
        DropwizardConfiguration configuration = RULE.getConfiguration();
        configuration.getDataSourceFactory().setCheckConnectionOnConnect(false);
        DatabaseHealthCheck databaseHealthCheck = new DatabaseHealthCheck(configuration);

        // Act
        HealthCheck.Result result = databaseHealthCheck.check();

        // Assert
        assertFalse(result.isHealthy());
        assertEquals(result.getMessage(), HELTHCHECK_MESSAGE);
    }

    @Test
    public void checkTrue() throws Exception {
        // Arrange
        DropwizardConfiguration configuration = RULE.getConfiguration();
        configuration.getDataSourceFactory().setCheckConnectionOnConnect(true);
        DatabaseHealthCheck databaseHealthCheck = new DatabaseHealthCheck(configuration);

        // Act
        HealthCheck.Result result = databaseHealthCheck.check();

        // Assert
        assertTrue(result.isHealthy());
    }
}
