package com.jwt.project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.jwt.project.security.JwtAuthenticationEntryPoint;
import com.jwt.project.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {

	
	 @Autowired
	    private JwtAuthenticationEntryPoint point;
	 
	 
	    @Autowired
	    private JwtAuthenticationFilter filter;
	
	    
	    
	    
	    @Bean
	    
	    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception
	    {
			
	    	
	    	//Configuration
	    	
	    	http.csrf(csrf->csrf.disable())
	    	.cors(cors->cors.disable())
	    	.authorizeHttpRequests(auth->auth.requestMatchers("/home/**").authenticated()
	    			.requestMatchers("/auth/login").permitAll()
	    			.anyRequest().authenticated())
	    	
	    	.exceptionHandling(ex->ex.authenticationEntryPoint(point))
	    	.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));//shouldnt store anything in server (stateless)
	    	
	    	
	    	
	    	
	    	http.addFilterBefore(filter,UsernamePasswordAuthenticationFilter.class);
	    	return http.build();
	    	
	    }
	    
	    
}
