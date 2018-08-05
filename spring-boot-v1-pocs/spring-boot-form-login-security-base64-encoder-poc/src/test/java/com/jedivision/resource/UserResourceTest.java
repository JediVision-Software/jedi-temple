package com.jedivision.resource;

import com.jedivision.entity.User;
import com.jedivision.service.UserService;
import com.jedivision.test.InitializationUtils;
import com.jedivision.test.RandomUtils;
import com.jedivision.test.runner.AbstractResourceRunner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

import static com.jedivision.test.RandomUtils.randomString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.security.core.authority.AuthorityUtils.commaSeparatedStringToAuthorityList;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class UserResourceTest extends AbstractResourceRunner {

    @Autowired private UserResource resourceUnderTest;

    @Autowired private UserService userService;

    private static final List<GrantedAuthority> ADMIN = commaSeparatedStringToAuthorityList("ADMIN");
    private static final List<GrantedAuthority> USER = commaSeparatedStringToAuthorityList("USER");

    @Before
    public void before() throws Exception {
        beforeByResource(resourceUnderTest);
        reset(userService);
    }

    @After
    public void after() {
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void findAllNoUser() throws Exception {
        // Act + Assert
        mvc.perform(get("/users")
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isFound());
    }

    @Test
    public void findAllUserAuthority() throws Exception {
        // Act + Assert
        mvc.perform(get("/users")
            .with(user(randomString()).password(randomString()).authorities(USER))
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    public void findAllAdminAuthority() throws Exception {
        // Arrange
        int size = RandomUtils.randomSmallInteger();
        List<User> users = InitializationUtils.list(User.class, size);
        when(userService.findAll()).thenReturn(users);

        // Act + Assert
        mvc.perform(get("/users")
            .with(user(randomString()).password(randomString()).authorities(ADMIN))
            .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(size)));

        // Assert
        verify(userService).findAll();
    }
}
