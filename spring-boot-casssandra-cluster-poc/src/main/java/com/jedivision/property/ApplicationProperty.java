package com.jedivision.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
@Data
public class ApplicationProperty {
    private ServerProperty server;
    private CassandraProperty cassandra;
    private ProfileProperty profile;
}
