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

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable();
        // 일단은 로그인하지 않더라도 모든 페이지에 접근할 수 있게 설정.
//        http.authorizeHttpRequests().requestMatchers(
//                new AntPathRequestMatcher("/**")
//        ).permitAll();

        //우선적으로, 족보 페이지만 접근 통제 설정.
       http
               .authorizeHttpRequests((authz) -> authz
                       .requestMatchers("/exam").hasRole("USER")
                       .anyRequest().permitAll()
               );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
