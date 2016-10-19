package com.jedivision.security;

import com.jedivision.entity.Role;
import com.jedivision.entity.User;
import com.jedivision.service.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static com.jedivision.test.RandomUtils.randomString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class H2UserDetailsServiceTest {

    @Configuration
    static class ContextConfiguration {

        @Bean
        UserService userService() {
            return mock(UserService.class);
        }

        @Bean
        H2UserDetailsService h2UserDetailsService() {
            return new H2UserDetailsService(userService());
        }

    }

    @Autowired private UserService userService;

    @Autowired private H2UserDetailsService serviceUnderTest;

    @Before
    public void before() {
        reset(userService);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(userService);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void loadUserByUserName() throws Exception {
        // Arrange
        String username = randomString();
        User user = User.builder().role(Role.ADMIN).build();
        when(userService.findByUsername(username)).thenReturn(user);


        // Act
        UserDetails userDetails = serviceUnderTest.loadUserByUsername(username);

        // Assert
        List<GrantedAuthority> authorities = (List<GrantedAuthority>) userDetails.getAuthorities();
        assertThat(authorities.size(), equalTo(1));
        assertThat(authorities.iterator().next().getAuthority(), equalTo("ADMIN"));
        assertThat(userDetails.getUsername(), equalTo(user.getUsername()));
        assertThat(userDetails.getPassword(), equalTo(user.getPassword()));
        assertThat(userDetails.isAccountNonExpired(), equalTo(true));
        assertThat(userDetails.isAccountNonLocked(), equalTo(true));
        assertThat(userDetails.isCredentialsNonExpired(), equalTo(true));
        assertThat(userDetails.isEnabled(), equalTo(true));
        verify(userService).findByUsername(username);
    }
}
