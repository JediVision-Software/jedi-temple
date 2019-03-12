package com.forcelate.configuration.runner;

import com.forcelate.acceptance.runner.AcceptanceAbstractRunner;
import com.forcelate.configuration.ApplicationPropertiesHolder;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        ApplicationPropertiesHolder.class

})
public abstract class AbstractResourceRunner extends AcceptanceAbstractRunner {
}
