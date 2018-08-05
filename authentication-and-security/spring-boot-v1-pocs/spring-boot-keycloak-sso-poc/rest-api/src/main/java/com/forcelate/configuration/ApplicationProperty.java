package com.forcelate.configuration;

import com.forcelate.properties.AppConfigs;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
@Data
public class ApplicationProperty {
    private AppConfigs appConfigs;
}
