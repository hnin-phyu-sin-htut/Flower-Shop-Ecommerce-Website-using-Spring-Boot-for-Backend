package com.example.demo.security;

import java.util.List;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) {
		return config.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.httpBasic(Customizer.withDefaults());
		httpSecurity.cors(c -> {
			CorsConfigurationSource source = new CorsConfigurationSource() {
				
				@Override
				public @Nullable CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
					CorsConfiguration config = new CorsConfiguration();
					config.setAllowCredentials(true);
					config.addAllowedMethod("*");
					config.addAllowedHeader("*");
					config.addExposedHeader("*");
					config.setAllowedOriginPatterns(List.of("http://localhost:5173"));
					return config;
				}
			};
			c.configurationSource(source);
		});
		
		httpSecurity.authorizeHttpRequests(c -> {
			c.requestMatchers("/products").permitAll();
            c.requestMatchers("/api/cart/**").authenticated();
			c.requestMatchers("/api/auth/**").permitAll();
			c.anyRequest().authenticated();
		});
		httpSecurity.csrf(c -> c.disable());
		return httpSecurity.build();
	}

}
