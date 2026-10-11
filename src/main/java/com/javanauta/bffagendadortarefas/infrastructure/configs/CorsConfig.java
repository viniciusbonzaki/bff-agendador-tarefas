package com.javanauta.bffagendadortarefas.infrastructure.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {
    @Bean
    public WebMvcConfigurer corsConfigs() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Configurar acessos dos Endpoints por URIs (/** -> Todos os Enpoints de todas as Controllers)
                registry.addMapping("/**")
                        // Configurar quais Domínios podem realizar as Requisições (3000/4200 -> FrontEnd)
                        .allowedOrigins("https//localhost:3000", "http://localhost:4200")
                        // Configurar quais Métodos podem ser utilizados
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
                        // Permitir os tipos de Headers que podem ser enviados na Requisição (JWT Token por Exemplo)
                        .allowedHeaders("*")
                        // Permitir o envio de Cookies e Headers no Autorization
                        .allowCredentials(true)
                        // Tempo de Cache do Navegador
                        .maxAge(360);
            }
        };
    }
}
