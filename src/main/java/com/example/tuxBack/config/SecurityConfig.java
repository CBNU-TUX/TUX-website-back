package com.example.tuxBack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // 일단은 로그인하지 않더라도 모든 페이지에 접근할 수 있게 설정.
   @Bean
   SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
       http.csrf().disable();
       http.authorizeHttpRequests().requestMatchers(
               new AntPathRequestMatcher("/**")
       ).permitAll();

       return http.build();
   }

   @Bean
    PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
   }

}
