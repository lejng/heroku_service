package com.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class EncodePasswordUtils {
    public static String encodePassword(String password) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(password);
    }
}
