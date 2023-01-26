package it.ko2.spring.example.spnego.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;

@EnableMethodSecurity(
    securedEnabled = true,
    jsr250Enabled = true)
@Configuration
public class MethodSecurityConfig {
}
