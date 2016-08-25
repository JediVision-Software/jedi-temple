package com.jedivision;

import org.apache.camel.Handler;
import org.springframework.stereotype.Component;

@Component
public class YodaBean {
    @Handler
    public String appendYoda(String msg) {
        return msg + " © Yoda";
    }
}
