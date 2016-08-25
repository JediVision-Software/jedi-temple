package com.jedivision;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.language.ConstantExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@Slf4j
public class Application {

    private Application() {}

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationBeans.class);
        CamelContext context = ctx.getBean(CamelContext.class);
        ObiWanBean obiWanBean = ctx.getBean(ObiWanBean.class);
        String address = ctx.getEnvironment().getProperty("application.smtp.email.address");
        String password = ctx.getEnvironment().getProperty("application.smtp.email.password");
        String to = ctx.getEnvironment().getProperty("application.smtp.send.to");
        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                    from("stream:in")
                            .setHeader("subject", new ConstantExpression("Hello, Jedi!"))
                            .bean(obiWanBean, "exitCheck")
                            .to("smtps://smtp.gmail.com:465" +
                                    "?username=" + address +
                                    "&password=" + password +
                                    "&to=" + to);
                }
            });
            context.start();
            log.info("Please, input some Jedi phrase");
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
