package com.jedivision.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Component
public class PrincipalUtils {

    private final HttpServletRequest request;

    @Autowired
    public PrincipalUtils(HttpServletRequest request) {
        this.request = request;
    }

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public Principal getPrincipal() {
        return request.getUserPrincipal();
    }

    public UserDetails getUserDetails() {
        return (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
