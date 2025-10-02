package com.example.SpringSecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class CustomSecurityConfig {
    @Autowired
    private UserDetailsService userDetailService;

    @Bean
    public SecurityFilterChain secFilterChain(HttpSecurity httpSecurity) throws Exception {

//        //disable csrf token authentication
//        httpSecurity.csrf(customizer->customizer.disable());
//        //enable authorization for all requests
//        httpSecurity.authorizeHttpRequests(request->request.anyRequest().authenticated());
//        //enable form login for authentication
////        httpSecurity.formLogin(Customizer.withDefaults());
//        //enable basic authentication for accessing the api from postman or any other code without form login
//        httpSecurity.httpBasic(Customizer.withDefaults());
//        //enable stateless session management to avoid csrf token authentication and use different session id for each request
//        httpSecurity.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        //create and return filter chain for the above configurations
//        return httpSecurity.build();

//        OR we can use builder pattern as below

        return httpSecurity
                .csrf(customizer->customizer.disable())
                .authorizeHttpRequests(request->request.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
        return provider;

    }
}
