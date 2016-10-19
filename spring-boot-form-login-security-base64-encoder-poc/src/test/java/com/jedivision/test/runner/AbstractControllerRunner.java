package com.jedivision.test.runner;

import com.jedivision.configuration.ApplicationControllerSecurityBeans;
import com.jedivision.configuration.ApplicationMVC;
import com.jedivision.configuration.ApplicationSecurity;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
        ApplicationControllerSecurityBeans.class,
        ApplicationMVC.class,
        ApplicationSecurity.class
})
@WebAppConfiguration
public abstract class AbstractControllerRunner {

    @Autowired private FilterChainProxy filterChainProxy;

    protected MockMvc mvc;

    private ViewResolver viewResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode("XHTML");
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver);
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(engine);
        return viewResolver;
    }

    protected void beforeByController(Object controllerUnderTest) {
        mvc = MockMvcBuilders
                .standaloneSetup(controllerUnderTest)
                .addFilter(filterChainProxy)
                .dispatchOptions(true)
                .setViewResolvers(viewResolver())
                .build();
    }
}
