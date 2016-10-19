package com.jedivision.controller;

import com.jedivision.entity.User;
import com.jedivision.service.UserService;
import com.jedivision.test.runner.AbstractControllerRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

import static com.jedivision.test.InitializationUtils.list;
import static com.jedivision.test.RandomUtils.randomSmallInteger;
import static com.jedivision.test.RandomUtils.randomString;
import static org.hamcrest.Matchers.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class IndexControllerTest extends AbstractControllerRunner {

    @Autowired private IndexController controllerUnderTest;

    @Autowired private UserService userService;

    private static final List<GrantedAuthority> ADMIN = commaSeparatedStringToAuthorityList("ADMIN");
    private static final List<GrantedAuthority> USER = commaSeparatedStringToAuthorityList("USER");

    @Before
    public void before() throws Exception {
        beforeByController(controllerUnderTest);
        reset(userService);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void indexNoUser() throws Exception {
        // Act + Assert
        mvc.perform(get("/index"))
                .andExpect(status().isFound());
    }

    @Test
    public void indexUserAuthority() throws Exception {
        // Arrange
        int size = randomSmallInteger();
        List<User> usersWithoutCurrent = list(User.class, size);
        List<User> users = list(User.class, size);
        when(userService.findUsersExceptCurrentUser()).thenReturn(usersWithoutCurrent);
        when(userService.findAll()).thenReturn(users);

        // Act + Assert
        mvc.perform(get("/index")
            .with(user(randomString()).password(randomString()).authorities(USER)))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.INDEX))
                .andExpect(model().attribute("users", equalTo(users)))
                .andExpect(model().attribute("usersWithoutCurrent", equalTo(usersWithoutCurrent)));

        // Assert
        verify(userService).findAll();
        verify(userService).findUsersExceptCurrentUser();
    }

    @Test
    public void indexAdminAuthority() throws Exception {
        // Arrange
        int size = randomSmallInteger();
        List<User> usersWithoutCurrent = list(User.class, size);
        List<User> users = list(User.class, size);
	    when(userService.findUsersExceptCurrentUser()).thenReturn(usersWithoutCurrent);
        when(userService.findAll()).thenReturn(users);

        // Act + Assert
        mvc.perform(get("/index")
			.with(user(randomString()).password(randomString()).authorities(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(view().name(Pages.INDEX))
                .andExpect(model().attribute("users", equalTo(users)))
                .andExpect(model().attribute("usersWithoutCurrent", equalTo(usersWithoutCurrent)))
                .andExpect(model().attribute("user", hasProperty("username", isEmptyOrNullString())))
                .andExpect(model().attribute("user", hasProperty("password", isEmptyOrNullString())))
                .andExpect(model().attribute("user", hasProperty("role", isEmptyOrNullString())));

        // Assert
        verify(userService).findAll();
        verify(userService).findUsersExceptCurrentUser();
    }

    @Test
    public void saveNoUser() throws Exception {
        // Act + Assert
        mvc.perform(post("/addUser")
            .with(csrf()))
                .andExpect(status().isFound());
    }

    @Test
    public void saveUserAuthority() throws Exception {
        // Act + Assert
        mvc.perform(post("/addUser")
            .with(csrf())
            .with(user(randomString()).password(randomString()).authorities(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    public void saveAdminAuthority() throws Exception {
        // Arrange
        User user = User.builder().build();
        when(userService.save(user)).thenReturn(user);

        mvc.perform(post("/addUser")
            .with(csrf())
            .with(user(randomString()).password(randomString()).authorities(ADMIN))
            .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isFound())
                .andExpect(view().name("redirect:/index"))
                .andExpect(model().attribute("user", equalTo(user)))
                .andExpect(model().attribute("user", hasProperty("username", isEmptyOrNullString())))
                .andExpect(model().attribute("user", hasProperty("password", isEmptyOrNullString())))
                .andExpect(model().attribute("user", hasProperty("role", isEmptyOrNullString())));

        // Assert
        verify(userService).save(any(User.class));
    }
}
