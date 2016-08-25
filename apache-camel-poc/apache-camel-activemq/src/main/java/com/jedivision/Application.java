package com.jedivision;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.apache.activemq.camel.component.ActiveMQComponent.activeMQComponent;

@Slf4j
public class Application {

    private Application() {}

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationBeans.class);
        CamelContext context = ctx.getBean(CamelContext.class);
        try {
            context.addComponent("activemq", activeMQComponent("vm://localhost?broker.persistent=false"));
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                    from("activemq:queue:jedi.queue")
                            .to("stream:out");
                }
            });
            ProducerTemplate template = context.createProducerTemplate();
            context.start();
            template.sendBody("activemq:jedi.queue", "In my experience there is no such thing as luck.");
            Thread.sleep(1000);
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
