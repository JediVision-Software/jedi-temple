package com.jedivision.feign;

import com.jedivision.api.Instance1;
import com.jedivision.api.Instance2;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FeignBuilder {

    @Value("${application.instance1}")
    private String instance1;
    @Value("${application.instance2}")
    private String instance2;

    public Instance1 instance1() {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .target(Instance1.class, instance1);
    }

    public Instance2 instance2() {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .target(Instance2.class, instance2);
    }
}
