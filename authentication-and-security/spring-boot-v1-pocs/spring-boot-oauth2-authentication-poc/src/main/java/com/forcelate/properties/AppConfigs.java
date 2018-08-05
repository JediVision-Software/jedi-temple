package com.forcelate.properties;

import com.forcelate.properties.nested.Cors;
import com.forcelate.properties.nested.OAuth;
import lombok.Data;

@Data
public class AppConfigs {
    private OAuth oauth;
    private Cors cors;
}
