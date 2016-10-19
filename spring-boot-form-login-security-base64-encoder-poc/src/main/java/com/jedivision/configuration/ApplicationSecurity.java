package com.jedivision.configuration;

import com.jedivision.controller.Pages;
import com.jedivision.security.H2UserDetailsService;
import com.jedivision.utils.Base64PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurity extends WebSecurityConfigurerAdapter {

    @Autowired private H2UserDetailsService h2UserDetailsService;
    @Autowired private Base64PasswordEncoder base64PasswordEncoder;

    private static final String LOGIN_URL = "/" + Pages.LOGIN;
    private static final String INDEX_URL = "/" + Pages.INDEX;
    private static final String USERS_URL = "/users";
    private static final String ADD_USER_URL = "/addUser";

    private static final String ADMIN_ROLE = "ADMIN";
    private static final String USER_ROLE = "USER";

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(h2UserDetailsService);
        authProvider.setPasswordEncoder(base64PasswordEncoder);
        return authProvider;
    }

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers(INDEX_URL).hasAnyAuthority(ADMIN_ROLE, USER_ROLE)
                .antMatchers(USERS_URL).hasAuthority(ADMIN_ROLE)
                .antMatchers(ADD_USER_URL).hasAuthority(ADMIN_ROLE)
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage(LOGIN_URL)
                    .permitAll()
                    .loginProcessingUrl(LOGIN_URL)
                    .defaultSuccessUrl(INDEX_URL)
            .and()
            .logout()
                .permitAll();

        http.csrf().ignoringAntMatchers(LOGIN_URL, USERS_URL);
    }
}
