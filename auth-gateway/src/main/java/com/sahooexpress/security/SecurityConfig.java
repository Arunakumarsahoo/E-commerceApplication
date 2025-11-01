package com.sahooexpress.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import org.springframework.security.core.userdetails.UserDetails;


import com.sahooexpress.user.dto.RoleBasedAuthority;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    	
    	http.cors(cors -> cors.disable())
    		.csrf(csrf ->csrf.disable())
    		.authorizeHttpRequests(auth -> auth
    			.requestMatchers("/api/authenticate/**").permitAll()
    			.requestMatchers("/api/users/**").permitAll() 
    			//.requestMatchers("/api/products/**").hasAnyRole(RoleBasedAuthority.ROLE_USER.getRole())
    			.requestMatchers("/api/products/**").authenticated()
    			.requestMatchers("/api/payments/**").authenticated()
    			.requestMatchers("/api/orders/**").authenticated()
    			.requestMatchers("/api/**")
    			.hasAnyRole(RoleBasedAuthority.ROLE_USER.getRole(),
    					RoleBasedAuthority.ROLE_ADMIN.getRole())
    			.anyRequest().authenticated())
    			.sessionManagement(session -> 
    					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
    			.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    	
    	return http.build();
    }

}
