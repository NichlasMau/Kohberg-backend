package com.example.kohbergbackend.security.config;

import com.example.kohbergbackend.security.JwtAuthenticationEntryPoint;
import com.example.kohbergbackend.security.JwtFilter;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@AllArgsConstructor
@CrossOrigin("*")
public class SecurityConfiguration implements WebMvcConfigurer {
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    private JwtFilter filter;
    private static PasswordEncoder passwordEncoder;
    @Bean
    public static PasswordEncoder passwordEncoder() {
        if(passwordEncoder==null){
            passwordEncoder = new BCryptPasswordEncoder();
        }
        return passwordEncoder;
    }

    /*

    ### FIX DEPRECATED METHODS

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        System.out.println("WebSec configure(HttpSecurity) Call: 2");
        http.cors().and().csrf().disable()
        .authorizeRequests()
        .antMatchers("/signup", "/login").permitAll()
        .anyRequest().authenticated()
        .and()
        .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

     */

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        System.out.println("addCorsMappings called");
        registry.addMapping("/**")  // /** means match any string recursively
                .allowedOriginPatterns("*") //Multiple strings allowed. Wildcard * matches all port numbers.
//                .allowedOriginPatterns("https://icy-grass-0a8c39803.4.azurestaticapps.net:*", "http://localhost:*") //Multiple strings allowed. Wildcard * matches all port numbers.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "HEAD", "OPTIONS") // decide which methods to allow
                //.allowedHeaders("*")
                .allowCredentials(true);
    }


}
