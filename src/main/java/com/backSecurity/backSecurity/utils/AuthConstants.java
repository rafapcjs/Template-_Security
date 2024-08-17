package com.backSecurity.backSecurity.utils;

public class AuthConstants {
    // Constructor privado para evitar la instanciación
    private AuthConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";
    public static final String ROLE_CLAIM = "role";
    public static final long JWT_EXPIRATION_TOKEN = 3600000; //equivaler a 5 min, donde 60000 = a 1 min

    public static final String CONTENT_TYPE_JSON = "application/json";
}