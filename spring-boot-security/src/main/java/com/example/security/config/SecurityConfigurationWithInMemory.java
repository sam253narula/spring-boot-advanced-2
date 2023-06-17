//package com.example.security.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
//
//import lombok.extern.slf4j.Slf4j;
//
//import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;
//
//@EnableWebSecurity
//@Configuration
//@Slf4j
//public class SecurityConfigurationWithInMemory {
//
//	@Bean
//	public UserDetailsService users() {
//		UserDetails user = User.builder().username("samarth")
////				.password("samarth")
//				.password(getPasswordEncoder().encode("samarth"))
//				.roles("STORE_OWNER").build();
//		
//		UserDetails admin = User.builder().username("rohan").password(getPasswordEncoder().encode("rohan"))
//				.roles("STORE_CLERK").build();
////		return new InMemoryUserDetailsManager(user);
//		return new InMemoryUserDetailsManager(user, admin);
//		//return new InMemoryUserDetailsManager(user);
//	}
//
//	// If you don't want to encode the created password, you can write the below
//	// bean method
//	// FYI: not recommended for Prod env
//	@Bean
//	PasswordEncoder getPasswordEncoder() {
////		return NoOpPasswordEncoder.getInstance();
//		return new BCryptPasswordEncoder();
//	}
//
//	// To Ignore Spring Boot Security to add authentication for H2 console
//	@Bean
//	public WebSecurityCustomizer webSecurityCustomizer() {
//		return (web) -> web.ignoring().requestMatchers(toH2Console());
//	}
//
//}
