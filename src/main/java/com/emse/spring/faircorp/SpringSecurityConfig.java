package com.emse.spring.faircorp;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

public class SpringSecurityConfig {
    private static final String ROLE_USER = "USER";

    @Bean
    public UserDetailsService userDetailsService() {
        // We create a password encoder
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(
                User.withUsername("user").password(encoder.encode("myPassword")).roles(ROLE_USER).build()
        );
        return manager;
    }

    @Bean
    @Order(1) // (1)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .antMatcher("/api/**") // (2)
                .authorizeRequests(authorize -> authorize.anyRequest().hasRole("ADMIN")) // (3)
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .build();
    }
}
