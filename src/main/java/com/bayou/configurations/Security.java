package com.bayou.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by joshuaeaton on 3/27/17.
 */
@Configuration
public class Security extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()   //disable csrf being needed for header in a request
                .authorizeRequests()    //authorize the following request based on following set rules
                .antMatchers(HttpMethod.OPTIONS, "**").permitAll() //allow any user to access this when OPTIONS
                .antMatchers(HttpMethod.GET,"**/service/v1/images/**").permitAll() //allows GETS for given route permitted to all users
                .anyRequest().authenticated() //catch all: this implies that if nothing matches the above two patterns, then require authentication
                .and().httpBasic(); //utilize http basic for authentication

    }


}
