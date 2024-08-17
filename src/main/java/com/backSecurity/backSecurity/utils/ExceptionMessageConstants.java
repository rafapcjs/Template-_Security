package com.backSecurity.backSecurity.utils;

/**
 * Clase que contiene constantes para mensajes de error y éxito utilizados en la API.
 * Esta clase no debe ser instanciada.
 */
public final class ExceptionMessageConstants {

    // Constructor privado para evitar la instanciación
    private ExceptionMessageConstants() {
        throw new UnsupportedOperationException("Esta es una clase de utilidad y no puede ser instanciada");
    }

    /**
     * Mensajes de error relacionados con la autenticación.
     */
    public static final String AUTH_UNAUTHORIZED_MSG = "No estás autorizado para realizar esta acción.";
    public static final String AUTH_INVALID_CREDENTIALS_MSG = "Credenciales inválidas.";
    public static final String AUTH_EXPIRED_TOKEN_MSG = "El token ha expirado.";
    public static final String AUTH_INVALID_TOKEN_MSG = "Token inválido.";
    public static final String AUTH_USER_NOT_FOUND_MSG = "Usuario no encontrado.";

    /**
     * Mensajes de error relacionados con las operaciones generales.
     */
    public static final String OPERATION_FAILED_MSG = "La operación falló. Inténtalo de nuevo.";
    public static final String RESOURCE_NOT_FOUND_MSG = "Recurso no encontrado.";
    public static final String INVALID_INPUT_MSG = "Entrada inválida.";
    public static final String DUPLICATE_ENTRY_MSG = "Ya existe un recurso con los mismos datos.";
    public static final String ACCESS_DENIED_MSG = "Acceso denegado.";

    /**
     * Mensajes de error relacionados con la validación de datos.
     */
    public static final String VALIDATION_ERROR_MSG = "Error de validación.";
    public static final String FIELD_REQUIRED_MSG = "El campo es requerido.";
    public static final String FIELD_TOO_SHORT_MSG = "El campo es demasiado corto.";
    public static final String FIELD_TOO_LONG_MSG = "El campo es demasiado largo.";
    public static final String FIELD_FORMAT_ERROR_MSG = "Formato de campo incorrecto.";

    /**
     * Mensajes para respuestas generales de la API.
     */
    public static final String INTERNAL_SERVER_ERROR_MSG = "Ha ocurrido un error inesperado. Por favor, intenta de nuevo más tarde.";
    public static final String SUCCESS_OPERATION_MSG = "Operación realizada con éxito.";
}
