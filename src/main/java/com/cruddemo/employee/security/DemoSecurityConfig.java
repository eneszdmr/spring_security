package com.cruddemo.employee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.HttpHandler;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

import static javax.print.attribute.standard.ReferenceUriSchemesSupported.HTTP;

@Configuration
public class DemoSecurityConfig {

    @Bean
    public JdbcUserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/employees").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET, "/employees/**").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.POST, "/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/employees/**").hasRole("ADMIN"));
    http.httpBasic();
    http.csrf().disable();

    return http.build();
    }
}
/**
 *
 *    @Bean
 *     public InMemoryUserDetailsManager userDetailsManager() {
 *         UserDetails abduselam = User.builder()
 *                 .username("asgundogdu")
 *                 .password("{noop}test123")
 *                 .roles("EMPLOYEE")
 *                 .build();
 *
 *         UserDetails ramazan = User.builder()
 *                 .username("ramazan")
 *                 .password("{noop}test123")
 *                 .roles("EMPLOYEE", "MANAGER")
 *                 .build();
 *
 *         UserDetails bugra = User.builder()
 *                 .username("bugra")
 *                 .password("{noop}test123")
 *                 .roles("EMPLOYEE", "MANAGER", "ADMIN")
 *                 .build();
 *
 *         return new InMemoryUserDetailsManager(abduselam, ramazan, bugra);
 *     }
 */