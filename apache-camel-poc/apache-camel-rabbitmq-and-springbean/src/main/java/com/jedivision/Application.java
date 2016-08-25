package com.jedivision;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Application {

    private Application() {}

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationBeans.class);
        CamelContext context = ctx.getBean(CamelContext.class);
        YodaBean yodaBean = ctx.getBean(YodaBean.class);
        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                    from("rabbitmq://localhost:5672/jedi?autoDelete=false&queue=yoda")
                            .bean(yodaBean, "appendYoda")
                            .to("stream:out");
                }
            });
            ProducerTemplate template = context.createProducerTemplate();
            context.start();
            template.sendBody("rabbitmq://localhost:5672/jedi?autoDelete=false&queue=yoda",
                    "Do or do not, there is no try!");
            Thread.sleep(1000);
        }
        finally {
            try {
                context.stop();
            } catch (Exception e) {
                log.error("Exception during stopping Apache Camel context {}", e);
            }
        }
        log.info("May the force be with you!");
        System.exit(0);
    }
}
