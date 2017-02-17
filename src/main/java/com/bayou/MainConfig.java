package com.bayou;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Joshua Eaton on 2/8/17.
 */
@Configuration
@EnableWebMvc
@EnableJpaRepositories
public class MainConfig extends WebMvcConfigurerAdapter {
    @Bean
    public BasicDataSource dataSource() throws URISyntaxException {
        URI dbUri = new URI(System.getenv("DATABASE_URL"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath() + "?currentSchema=public";

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        .allowedMethods("PUT", "DELETE", "GET", "POST")
        .allowCredentials(true).maxAge(3600);
    }
    //add the below back once a postman whitelist solution is found
    //.allowedOrigins("http://localhost:3000","https://uno-shareboard-webapp-dev.herokuapp.com/","https://uno-shareboard-webapp-staging.herokuapp.com/","https://uno-shareboard-webapp-prod.herokuapp.com/")

}
