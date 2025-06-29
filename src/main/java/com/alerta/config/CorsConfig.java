package com.alerta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // aplica a todos los endpoints
                .allowedOrigins("http://localhost:3000") // frontend
                .allowedMethods("*") // GET, POST, PUT, etc.
                .allowedHeaders("*");
    }
}
