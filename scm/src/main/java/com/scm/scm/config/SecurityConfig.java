package com.scm.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.scm.scm.services.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {

    // @Bean
    // public UserDetailsService userDetailsService(){
    //     UserDetails userDetails = User.withDefaultPasswordEncoder().username("admin123").password("admin123").roles("ADMIN").build();
    //     var inMemoryUserDetailManager = new InMemoryUserDetailsManager(userDetails);
    //     return inMemoryUserDetailManager;
    // }/

    @Autowired
    SecurityCustomUserDetailService userDetailService;

    @Bean 
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider authenticationProvider =  new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity.authorizeHttpRequests(authorizer -> {
            authorizer.requestMatchers("/user/**").authenticated();
            authorizer.anyRequest().permitAll();
        });

        httpSecurity.formLogin(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
