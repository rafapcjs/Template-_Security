package com.backSecurity.backSecurity.configurations;

import com.backSecurity.backSecurity.configurations.filter.JwtTokenValidator;
import com.backSecurity.backSecurity.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity public class SecurityConfig {

    // Inyección de dependencia de JwtUtils para manejar tokens JWT
    @Autowired
    private JwtUtils jwtUtils;

    // Configuración de la cadena de filtros de seguridad
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthenticationProvider authenticationProvider) throws Exception {
        return httpSecurity
                // Deshabilita la protección CSRF (Cross-Site Request Forgery), ya que la aplicación es stateless
                .csrf(csrf -> csrf.disable())

                // Configura la gestión de sesiones para que sea stateless, ya que se usarán tokens JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Configura las reglas de autorización para las solicitudes HTTP
                .authorizeHttpRequests(http -> {
                    // Endpoints públicos accesibles sin autenticación
                    http.requestMatchers(HttpMethod.POST, "/auth/**").permitAll();

                    // Endpoints privados que requieren autenticación y autorización específica
                    http.requestMatchers(HttpMethod.GET, "/method/get").hasAuthority("READ");
                    http.requestMatchers(HttpMethod.POST, "/method/post").hasAuthority("CREATE");
                    http.requestMatchers(HttpMethod.DELETE, "/method/delete").hasAuthority("DELETE");
                    http.requestMatchers(HttpMethod.PUT, "/method/put").hasAuthority("UPDATE");

                    // Deniega todas las demás solicitudes que no coincidan con las reglas anteriores
                    http.anyRequest().denyAll();
                })

                // Agrega un filtro personalizado para validar los tokens JWT antes de procesar la autenticación básica
                .addFilterBefore(new JwtTokenValidator(jwtUtils), BasicAuthenticationFilter.class)

                // Construye la cadena de filtros configurada
                .build();
    }
}
