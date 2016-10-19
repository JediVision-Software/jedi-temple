package com.jedivision.service.impl;

import com.jedivision.dao.UserDao;
import com.jedivision.entity.User;
import com.jedivision.service.UserService;
import com.jedivision.test.InitializationUtils;
import com.jedivision.utils.Base64PasswordEncoder;
import com.jedivision.utils.PrincipalUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static com.jedivision.test.RandomUtils.randomSmallInteger;
import static com.jedivision.test.RandomUtils.randomString;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class UserServiceImplTest {

    @Configuration
    static class ContextConfiguration {

        @Bean
        UserDao userDao() { return mock(UserDao.class); }

        @Bean
        Base64PasswordEncoder encoder() { return mock(Base64PasswordEncoder.class); }

        @Bean
        PrincipalUtils principalUtils() { return mock(PrincipalUtils.class); }

        @Bean
        UserService userService() {
            return new UserServiceImpl(userDao(), encoder(), principalUtils());
        }

    }

    @Autowired private UserDao userDao;
    @Autowired private Base64PasswordEncoder encoder;
    @Autowired private PrincipalUtils principalUtils;

    @Autowired private UserService serviceUnderTest;

    @Before
    public void before() {
        reset(userDao, encoder, principalUtils);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(userDao, encoder, principalUtils);
    }

    @Test
    public void findUsersExceptCurrentUser() {
        // Arrange
        int size = randomSmallInteger();
        List<User> users = InitializationUtils.list(User.class, size);
        String username = randomString();
        Authentication authentication = mock(Authentication.class);
        when(userDao.findUsersExceptCurrentUser(username)).thenReturn(users);
        when(authentication.getName()).thenReturn(username);
        when(principalUtils.getAuthentication()).thenReturn(authentication);

        // Act
        List<User> result = serviceUnderTest.findUsersExceptCurrentUser();

        // Assert
        assertThat(result.size(), equalTo(size));
        verify(userDao).findUsersExceptCurrentUser(username);
        verify(principalUtils).getAuthentication();
    }

    @Test
    public void findAll() {
        // Arrange
        int size = randomSmallInteger();
        List<User> users = InitializationUtils.list(User.class, size);
        when(userDao.findAll()).thenReturn(users);

        // Act
        List<User> result = serviceUnderTest.findAll();

        // Assert
        assertThat(result.size(), equalTo(size));
        verify(userDao).findAll();
    }

    @Test
    public void findByUsernameThrowException() {
        // Arrange
        String username = randomString();
        when(userDao.findByUsername(username)).thenReturn(null);

        // Act + Exception Assert
        assertThatThrownBy(() -> serviceUnderTest.findByUsername(username))
                .isInstanceOf(UsernameNotFoundException.class)
                .hasMessage("User " + username + " not found in database");

        // Assert
        verify(userDao).findByUsername(username);
    }

    @Test
    public void findByUsername() {
        // Arrange
        String username = randomString();
        User user = InitializationUtils.entity(User.class);
        when(userDao.findByUsername(username)).thenReturn(user);

        // Act
        serviceUnderTest.findByUsername(username);

        // Assert
        verify(userDao).findByUsername(username);
    }

    @Test
    public void save() {
        // Arrange
        String password = randomString();
        String encodedPassword = randomString();
        User startUser = User.builder().password(password).build();
        User endUser = User.builder().password(encodedPassword).build();
        when(userDao.save(startUser)).thenReturn(endUser);
        when(encoder.encode(password)).thenReturn(encodedPassword);

        // Act
        serviceUnderTest.save(startUser);

        // Assert
        verify(encoder).encode(password);
        verify(userDao).save(startUser);
    }
}
