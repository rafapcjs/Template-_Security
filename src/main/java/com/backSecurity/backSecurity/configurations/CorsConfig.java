package com.backSecurity.backSecurity.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración global de CORS para permitir el acceso desde cualquier origen.
 * Esto es útil para permitir que cualquier frontend pueda interactuar con la API.
 */
@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Configura CORS para todos los endpoints de la API
                registry.addMapping("/**")
                        .allowedOrigins("*") // Permitir todos los orígenes
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Permitir los métodos HTTP más comunes
                        .allowedHeaders("*") // Permitir todos los encabezados
                        .exposedHeaders("*"); // Exponer todos los encabezados en la respuesta
            }
        };
    }
}