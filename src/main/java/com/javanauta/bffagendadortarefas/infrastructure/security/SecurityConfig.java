package com.javanauta.bffagendadortarefas.infrastructure.security;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

// Classe com as Configurações necessárias para conseguir utilizar JWT no Swagger OpenAPI
@SecurityScheme(name = SecurityConfig.SECURITY_SCHEME, type = SecuritySchemeType.HTTP,
                bearerFormat = "JWT", scheme = "bearer")
public class SecurityConfig {
    // Construtor apenas para evitar a Instância dessa Classe
    private SecurityConfig() {}

    // Configuração do Token para o tipo Bearer
    public static final String SECURITY_SCHEME = "bearerAuth";
}
