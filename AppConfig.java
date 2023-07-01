package com.jwt.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;



//this is used for password generation through java code
@Configuration
public class AppConfig
{

	@Bean
	public UserDetailsService userDetailService() {
		
	UserDetails user =User.builder().username("harsh").password(passwordEncoder().encode("abc")).roles("ADMIN").build();
	UserDetails user1 =User.builder().username("ram").password(passwordEncoder().encode("abc")).roles("ADMIN").build();

	
		return new InMemoryUserDetailsManager(user,user1);
	}
	
	
	// to encode the password given in the userDetailService() method
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	 @Bean
	    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
	        return builder.getAuthenticationManager();
	    }
	
}
