//package com.maciej.cars.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.SecurityBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//import org.springframework.security.web.SecurityFilterChain;
//
//import java.util.Arrays;
//
//@Configuration
//public class SecurityConfig {
//
//
//    @Bean
//    public PasswordEncoder getBcryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//    //TODO Make it secure.
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/**").anyRequest();
//    }
//
////    @BeanFactoryResolver
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
////                .csrf(Customizer.withDefaults()))
////                .antMatchers("/for-user").hasAnyRole("USER", "ADMIN")
////                .antMatchers("/for-admin").hasAuthority("ROLE_ADMIN")
////                .http.formLogin(); formLogin.permitAll()
////                .logout()
////                .logoutSuccessUrl("/bye").permitAll();
////        return http.build();
////    }
//
//
//
//}
//
