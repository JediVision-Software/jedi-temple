package com.jedivision;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class Application {

    private Application() {}

    public static void main(String[] args) throws Exception {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationBeans.class);
        CamelContext context = ctx.getBean(CamelContext.class);
        String ip = ctx.getEnvironment().getProperty("application.es.ip");
        String port = ctx.getEnvironment().getProperty("application.es.port");
        String clusterName = ctx.getEnvironment().getProperty("application.es.clusterName");
        String indexName = ctx.getEnvironment().getProperty("application.es.indexName");
        String indexType = ctx.getEnvironment().getProperty("application.es.indexType");
        try {
            context.addRoutes(new RouteBuilder() {
                @Override
                public void configure() {
                    from("direct:index")
                            .to("elasticsearch://" + clusterName + "?operation=INDEX" +
                                    "&indexName=" + indexName +
                                    "&indexType=" + indexType +
                                    "&ip=" + ip +
                                    "&port=" + port);
                }
            });
            Map<String, Object> jedi = new HashMap<>();
            jedi.put("fullName", "Master Yoda");
            jedi.put("age", 900);
            ProducerTemplate template = context.createProducerTemplate();
            context.start();
            String indexId = template.requestBody("direct:index", jedi, String.class);
            log.info("ElasticSearch IndexId: " + indexId);
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
