package com.jedivision.utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class PrincipalUtilsTest {

    @Configuration
    static class ContextConfiguration {

        @Bean
        HttpServletRequest request() {
            return mock(HttpServletRequest.class);
        }

        @Bean
        PrincipalUtils principalUtils() {
            return new PrincipalUtils(request());
        }

    }

    @Autowired private HttpServletRequest request;

    @Autowired private PrincipalUtils utilsUnderTest;

    @Before
    public void before() {
        reset(request);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(request);
    }

    @Test
    public void getAuthentication() {
        // Arrange
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(context);
        when(context.getAuthentication()).thenReturn(auth);

        // Act
        Authentication result = utilsUnderTest.getAuthentication();

        // Assert
        assertEquals(result, auth);
    }

    @Test
    public void getPrincipal() {
        // Arrange
        Principal principal = mock(Principal.class);
        when(request.getUserPrincipal()).thenReturn(principal);

        // Act
        Principal result = utilsUnderTest.getPrincipal();

        // Assert
        assertEquals(result, principal);
        verify(request).getUserPrincipal();
    }

    @Test
    public void getUserDetails() {
        // Arrange
        UserDetails userDetails = mock(UserDetails.class);
        Authentication auth = Mockito.mock(Authentication.class);
        SecurityContext context = Mockito.mock(SecurityContext.class);
        SecurityContextHolder.setContext(context);
        when(auth.getPrincipal()).thenReturn(userDetails);
        when(context.getAuthentication()).thenReturn(auth);

        // Act
        UserDetails result = utilsUnderTest.getUserDetails();

        // Assert
        assertEquals(result, userDetails);
    }
}
