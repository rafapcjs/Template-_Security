package com.backSecurity.backSecurity.configurations.filter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.backSecurity.backSecurity.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
public class JwtTokenValidator extends OncePerRequestFilter {

    // Inyección de dependencia de JwtUtils para validar y extraer información de los tokens JWT
    private final JwtUtils jwtUtils;

    // Constructor que recibe una instancia de JwtUtils
    public JwtTokenValidator(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    // Método que se ejecuta en cada solicitud (request) para filtrar y validar el token JWT
    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws ServletException, IOException {

        // Obtiene el token JWT del encabezado de autorización de la solicitud
        String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

        // Si el token no es nulo, procede a validarlo
        if (jwtToken != null) {
            // Elimina el prefijo "Bearer " del token
            jwtToken = jwtToken.substring(7);

            // Valida el token y obtiene el token JWT decodificado
            DecodedJWT decodedJWT = jwtUtils.validateToken(jwtToken);

            // Extrae el nombre de usuario del token decodificado
            String username = jwtUtils.extractUsername(decodedJWT);

            // Crea un contexto de seguridad vacío y establece la autenticación con el nombre de usuario
            SecurityContext context = SecurityContextHolder.createEmptyContext();
            Authentication authenticationToken = new UsernamePasswordAuthenticationToken(username, null, null);
            context.setAuthentication(authenticationToken);
            SecurityContextHolder.setContext(context);
        }

        // Continúa la cadena de filtros, permitiendo que la solicitud proceda
        filterChain.doFilter(request, response);
    }
}
