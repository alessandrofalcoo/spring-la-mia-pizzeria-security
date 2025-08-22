package org.lessons.java.spring_la_mia_pizzeria_crud.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/pizze/create", "/pizze/edit/**").hasAuthority("Admin")
                .requestMatchers(HttpMethod.POST, "/pizze/**").hasAuthority("Admin")
                .requestMatchers("/ingredients", "/ingredients/**").hasAuthority("Admin")
                .requestMatchers("/pizze", "/pizze/**").hasAnyAuthority("User", "Admin")
                .requestMatchers("/**").permitAll()

        )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/perform_login")
                        .defaultSuccessUrl("/pizze/index", true)
                        .failureUrl("/login?error=true")
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .exceptionHandling(Customizer.withDefaults())
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    DatabaseUserDetailService userDetailService() {
        return new DatabaseUserDetailService();
    }

    @Bean
    @SuppressWarnings("deprecation")
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailService());

        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
