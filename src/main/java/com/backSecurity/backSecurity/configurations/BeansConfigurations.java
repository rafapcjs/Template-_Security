package com.backSecurity.backSecurity.configurations;

 import com.backSecurity.backSecurity.serivce.CustomsDetailServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
 import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@Configuration
public class BeansConfigurations {

    // Define un bean para AuthenticationManager, que es responsable de gestionar la autenticación
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        // Retorna el AuthenticationManager configurado por Spring Security
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Define un bean para AuthenticationProvider, que se utiliza para autenticar a los usuarios
    @Bean
    public AuthenticationProvider authenticationProvider(CustomsDetailServices customsDetailServices) {
        // Crea una instancia de DaoAuthenticationProvider, que es una implementación de AuthenticationProvider
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

        // Configura el UserDetailsService personalizado (CustomsDetailServices) para buscar usuarios
        daoAuthenticationProvider.setUserDetailsService(customsDetailServices);

        // Configura el PasswordEncoder para cifrar y verificar contraseñas
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        // Retorna la instancia configurada de DaoAuthenticationProvider
        return daoAuthenticationProvider;
    }

    // Define un bean para PasswordEncoder, que se usa para cifrar contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        // Retorna una instancia de BCryptPasswordEncoder, un codificador de contraseñas seguro
        return new BCryptPasswordEncoder();
    }
}
