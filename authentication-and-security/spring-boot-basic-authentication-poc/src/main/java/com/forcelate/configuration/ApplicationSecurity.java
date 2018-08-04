package com.forcelate.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {
    private static final String USER_ROLE = "USER";
    private static final String ADMIN_ROLE = "ADMIN";

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeRequests()
                .antMatchers("/api/public").permitAll()
                .antMatchers("/api/private").hasRole(USER_ROLE)
                .antMatchers("/api/onlyAdmin").hasRole(ADMIN_ROLE)
                .and()
                    .httpBasic()
                .and()
                    .csrf().disable();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("user1").password("user1").roles(USER_ROLE)
                .and()
                .withUser("user2").password("user2").roles(USER_ROLE)
                .and()
                .withUser("admin").password("admin").roles(ADMIN_ROLE, USER_ROLE);
    }
}
