package com.jedivision.test.runner;

import com.jedivision.configuration.ApplicationMVC;
import com.jedivision.configuration.ApplicationResourceSecurityBeans;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {
        ApplicationResourceSecurityBeans.class,
        ApplicationMVC.class,
        ApplicationSecurity.class
})
@WebAppConfiguration
public abstract class AbstractResourceRunner {

    @Autowired
    private FilterChainProxy filterChainProxy;

    protected MockMvc mvc;

    protected void beforeByResource(Object resourceUnderTest) {
        mvc = MockMvcBuilders
                .standaloneSetup(resourceUnderTest)
                .addFilter(filterChainProxy)
                .dispatchOptions(true)
                .build();
    }
}
