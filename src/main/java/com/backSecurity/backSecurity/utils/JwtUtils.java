package com.backSecurity.backSecurity.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;
@Component
public class JwtUtils {

    // Inyecta el valor de la clave privada utilizada para firmar el token JWT desde el archivo de configuración
    @Value("${security.jwt.key.private}")
    private String privateKey;

    // Inyecta el nombre del generador de usuario (issuer) del token JWT desde el archivo de configuración
    @Value("${security.jwt.user.generator}")
    private String userGenerator;

    // Método para crear un token JWT basado en la autenticación proporcionada
    public String createToken(Authentication authentication) {
        // Utiliza la clave privada para crear un algoritmo HMAC256 para firmar el token
        Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
        // Extrae el nombre de usuario (principal) de la autenticación
        String username = authentication.getPrincipal().toString();

        // Crea un token JWT con varios detalles como el issuer, el nombre de usuario, un ID único,
        // la fecha de emisión, la fecha de expiración (30 minutos después), y la fecha en que es válido
        String token = JWT.create()
                .withIssuer(this.userGenerator)
                .withSubject(username)
                .withJWTId(UUID.randomUUID().toString())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 1800000)) // 30 minutos de validez
                .withNotBefore(new Date(System.currentTimeMillis())) // No válido antes de la fecha actual
                .sign(algorithm); // Firma el token con el algoritmo

        // Retorna el token JWT generado
        return token;
    }

    // Método para validar un token JWT y retornar el token decodificado si es válido
    public DecodedJWT validateToken(String token) {
        try {
            // Crea un algoritmo HMAC256 con la clave privada para verificar el token
            Algorithm algorithm = Algorithm.HMAC256(this.privateKey);
            // Configura un verificador JWT con el issuer esperado
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withIssuer(this.userGenerator)
                    .build();

            // Verifica y retorna el token JWT decodificado
            return jwtVerifier.verify(token);
        } catch (JWTVerificationException e) {
            // Si la verificación falla, lanza una excepción indicando que el token no es válido
            throw new JWTVerificationException("Token no válido", e);
        }
    }

    // Método para extraer el nombre de usuario (subject) de un token JWT decodificado
    public String extractUsername(DecodedJWT decodedJWT) {
        return decodedJWT.getSubject();
    }

    // Método para obtener un claim específico de un token JWT decodificado
    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName) {
        return decodedJWT.getClaim(claimName);
    }
}
