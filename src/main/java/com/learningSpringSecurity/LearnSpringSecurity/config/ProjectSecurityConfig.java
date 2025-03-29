package com.learningSpringSecurity.LearnSpringSecurity.config;

import com.learningSpringSecurity.LearnSpringSecurity.filter.JWTTokenGeneratorFilter;
import com.learningSpringSecurity.LearnSpringSecurity.filter.JWTValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(csrfConfig->csrfConfig.disable());
        http.headers(header-> header.frameOptions(frameOptions ->frameOptions.disable() ));

        http.sessionManagement(sessionConfig -> sessionConfig.
                sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class);
        http.addFilterBefore(new JWTValidatorFilter(),BasicAuthenticationFilter.class);

        http.authorizeHttpRequests(request -> request
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/welcome").permitAll()
                .requestMatchers("/user").permitAll()
                .requestMatchers("/accounts").hasAuthority("USER")
                .requestMatchers("/balances").hasAuthority("ADMIN")
                .anyRequest().authenticated());

        http.httpBasic(withDefaults());
        http.formLogin(withDefaults());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
