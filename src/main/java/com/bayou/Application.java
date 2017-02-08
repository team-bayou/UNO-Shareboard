package com.bayou;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by sev on 2/8/17.
 */
public class Application {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/service/v1/users/addUser").allowedOrigins("https://uno-shareboard-webapp-dev.herokuapp.com/");
                registry.addMapping("service/v1/stringResponse").allowedOrigins("https://uno-shareboard-webapp-dev.herokuapp.com/");
                registry.addMapping("/service/v1//stringResponseCustom").allowedOrigins("https://uno-shareboard-webapp-dev.herokuapp.com/");
            }
        };
    }
}
