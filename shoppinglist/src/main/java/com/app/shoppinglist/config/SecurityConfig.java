package com.app.shoppinglist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired
	private UserDetailsService userDetailsService; 
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		
		return httpSecurity.csrf(customizer -> customizer.disable())
		.authorizeHttpRequests(request-> request
				.requestMatchers("register", "login")
				.permitAll()
				.anyRequest().authenticated())
		//.formLogin(Customizer.withDefaults()); 
		
		.httpBasic(Customizer.withDefaults())
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)).build();
	}
	
	
@Bean
public AuthenticationProvider authenticationProvider() {
	DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(); 
	daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder(12));
	daoAuthenticationProvider.setUserDetailsService(userDetailsService);
	
	return daoAuthenticationProvider;
}

@Bean
public AuthenticationManager authenticationManager(AuthenticationConfiguration config ) throws Exception{
	return config.getAuthenticationManager();
}

}
