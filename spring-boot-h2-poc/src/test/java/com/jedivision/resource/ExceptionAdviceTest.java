package com.jedivision.resource;

import com.jedivision.exception.ApplicationException;
import com.jedivision.test.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class ExceptionAdviceTest {
    @Configuration
    static class ContextConfiguration {

        @Bean
        ExceptionAdvice exceptionAdvice() {
            return new ExceptionAdvice();
        }

    }

    @Autowired private ExceptionAdvice exceptionAdvice;

    @Test
    public void handleException() throws IOException {
        // Arrange
        ApplicationException exception = new ApplicationException(RandomUtils.randomString());
        HttpServletResponse response = mock(HttpServletResponse.class);

        // Act
        exceptionAdvice.handleException(exception, response);

        // Assert
        verify(response).sendError(eq(HttpStatus.INTERNAL_SERVER_ERROR.value()), eq(exception.getMessage()));
        verifyNoMoreInteractions(response);
    }
}
