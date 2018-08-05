package com.jedivision.utils;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Base64;

@Component
public class Base64PasswordEncoder implements PasswordEncoder {

   @Override
    public String encode(CharSequence rawPassword) {
        return Base64.getEncoder().encodeToString(rawPassword.toString().getBytes());
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String passwordUI = rawPassword.toString();
        String encodedPasswordUI = new String(Base64.getEncoder().encode(passwordUI.getBytes()));
        return encodedPassword.equals(encodedPasswordUI);
    }
}
