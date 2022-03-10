package com.example.Gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class ResourceServerConfig {

    @Bean
    SecurityFilterChain ResourceServerSecurityFilterChain(HttpSecurity http) throws Exception {
        http.mvcMatcher("/articles/**")
                .authorizeRequests()
                .mvcMatchers("/articles/**")
                .access("hasAuthority('SCOPE_articles.read')")
                .and()
                .oauth2ResourceServer()
                //we're also invoking the oauth2ResourceServer() method, which will configure the OAuth server connection based on the application.yml configuration.
                .jwt();
        return http.build();
    }

}
