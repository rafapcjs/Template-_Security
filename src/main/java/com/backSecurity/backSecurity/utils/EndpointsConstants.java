package com.backSecurity.backSecurity.utils;

/**
 * Clase que contiene las constantes para los endpoints de la API.
 * Esta clase no debe ser instanciada.
 */
public final class EndpointsConstants {

    // Constructor privado para evitar la instanciación
    private EndpointsConstants() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no puede ser instanciada");
    }

    // Base API path
    public static final String ENDPOINT_BASE_API = "/api/v1";

    // Auth endpoints
    public static final String ENDPOINT_AUTH = ENDPOINT_BASE_API + "/auth";
    public static final String ENDPOINT_AUTH_LOGIN = ENDPOINT_AUTH + "/login";
    public static final String ENDPOINT_AUTH_REGISTER = ENDPOINT_AUTH + "/register";
    public static final String ENDPOINT_AUTH_LOGOUT = ENDPOINT_AUTH + "/logout";

// Pattern for all endpoints
}