package com.tuapp.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class HashGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "77778888"; // tu contraseña real
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Hash generado: " + encodedPassword);
    }
}
