package com.jedivision.healthcheck;

import com.codahale.metrics.health.HealthCheck;
import com.jedivision.configuration.DropwizardConfiguration;

public class DatabaseHealthCheck extends HealthCheck {
    private final DropwizardConfiguration configuration;

    public DatabaseHealthCheck(DropwizardConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected Result check() throws Exception {
        if (configuration.getDataSourceFactory().getCheckConnectionOnConnect()) {
            return Result.healthy();
        } else {
            return Result.unhealthy("Cannot connect to " + configuration.getDataSourceFactory().getUrl());
        }
    }
}
