package com.jedivision;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Application {

    private Application() {}

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationBeans.class);
        CamelContext context = ctx.getBean(CamelContext.class);
        ObiWanBean obiWanBean = ctx.getBean(ObiWanBean.class);
        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                    from("jetty:http://localhost:8888/jedi")
                            .bean(obiWanBean, "process");
                    from("netty:tcp://localhost:7000?sync=true&textline=true")
                            .bean(obiWanBean, "process");
                }
            });
            context.start();
            ObiWanBean.getLatch().await();
        } finally {
            try {
                context.stop();
            } catch (Exception e) {
                log.error("Exception during stopping Apache Camel context {}", e);
            }
        }
        log.info("May the force be with you!");
    }
}
