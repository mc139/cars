package com.maciej.cars.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Arrays;

@Configuration
public class SecurityConfig {


//TODO make it work
    @Bean
    public PasswordEncoder getBcryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public InMemoryUserDetailsManager get() {
        UserDetails user = User.withUsername("jan")
                .password(getBcryptPasswordEncoder().encode("jan123"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(getBcryptPasswordEncoder().encode("admin123"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(Arrays.asList(user, admin));
    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(Customizer.withDefaults()))
//                .antMatchers("/for-user").hasAnyRole("USER", "ADMIN")
//                .antMatchers("/for-admin").hasAuthority("ROLE_ADMIN")
//                .http.formLogin(); formLogin.permitAll()
//                .logout()
//                .logoutSuccessUrl("/bye").permitAll();
//        return http.build();
//    }



}

