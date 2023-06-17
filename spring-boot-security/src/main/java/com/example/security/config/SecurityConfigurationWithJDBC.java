package com.example.security.config;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.DataSourceFactory;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfigurationWithJDBC {

	@Autowired
	DataSourceFactory dataSource;
	
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .generateUniqueName(false)
                .setName("Veggiesdb") 
                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
                .setDataSourceFactory(dataSource)
                .build();
    }

	@Bean
	public UserDetailsManager users(DataSource dataSource) {
		UserDetails userSamarth = User.builder().username("samarth").password(getPasswordEncoder().encode("samarth"))
				.roles("STORE_OWNER").build();

		UserDetails userRohan = User.builder().username("rohan").password(getPasswordEncoder().encode("rohan"))
				.roles("STORE_CLERK").build();
		JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
		users.createUser(userSamarth);
		users.createUser(userRohan);

		return users;
	}

	//If you don't want to encode the created password, you can write the below bean method, FYI: not recommended for Prod env
	@Bean
	public PasswordEncoder getPasswordEncoder() {
//		return NoOpPasswordEncoder.getInstance();
		return new BCryptPasswordEncoder();
	}

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests()
            .requestMatchers("/organicVeggies/viewFinancials", "/organicVeggies/makeAnnouncement")
            .hasRole("STORE_OWNER")
            .requestMatchers("/organicVeggies/checkInventory", "/organicVeggies/viewInventory", "/organicVeggies/doCheckout/")
            .hasAnyRole("STORE_OWNER", "STORE_CLERK").requestMatchers("/**").permitAll().and().formLogin();

        return http.build();
    }

	
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeHttpRequests()
//            .requestMatchers("/**").permitAll().and().formLogin();
//
//        return http.build();
//    }
    
	// Alternative fix, above filterChain also works : To Ignore Spring Boot Security to add authentication for H2 console
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers(toH2Console());
	}
	
    // Found fix for making JDBC Based Spring Security work: https://github.com/spring-projects/spring-security/issues/12546
//	  @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        http
//	                .authorizeHttpRequests(auth -> auth
//	                        .requestMatchers("/h2-console/**").permitAll()
//	                )
//	                .headers(headers -> headers.frameOptions().disable())
//	                .csrf(csrf -> csrf
//	                        .ignoringRequestMatchers("/h2-console/**"));
//	        return http.build();
//	    }

}
