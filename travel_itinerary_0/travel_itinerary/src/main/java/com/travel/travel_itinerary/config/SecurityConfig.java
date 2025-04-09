package com.travel.travel_itinerary.config;

import com.travel.travel_itinerary.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF for development (enable in production)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/auth/login", "/auth/register", "/css/**", "/js/**").permitAll() // ✅ Public access to home page
                        .requestMatchers("/dashboard", "/logout").authenticated() // Requires authentication
                        .anyRequest().authenticated()
                )
                .formLogin(login -> login
                        .loginPage("/auth/login") // Custom login page
                        .loginProcessingUrl("/do-login") // Spring Security login endpoint
                        .usernameParameter("email") // Use email for login
                        .passwordParameter("password")
                        .defaultSuccessUrl("/", true) // ✅ Redirect to home page after login
                        .failureUrl("/auth/login?error=true") // Redirect on login failure
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") // Logout endpoint
                        .logoutSuccessUrl("/") // ✅ Redirect to home page after logout
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                );

        return http.build();
    }
}
