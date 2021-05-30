package com.cognizant.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cognizant.authorization.filter.AuthFilter;

import lombok.extern.slf4j.Slf4j;


@SuppressWarnings("deprecation")
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter{

@Autowired
private UserDetailsService userDetailsService;

@Autowired
private AuthFilter filter;
	@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("AuthlogFilter  class  configure method called");
		auth.userDetailsService(userDetailsService);
		
		
		}
	
	@Bean
	public AuthenticationManager getAuthenticationManager() throws Exception {
		log.info("AuthlogFilter  class  getAuthenticationManager method called");
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		log.info("AuthlogFilter  class configure method called");
		http.csrf().disable()
					.authorizeRequests()
					.antMatchers("/user/**").hasAnyRole("USER")
					.antMatchers("/**").permitAll()
					.anyRequest()
					.authenticated();
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		log.info("AuthlogFilter  class  getPasswordEncodermethod called");
		return NoOpPasswordEncoder.getInstance();
	}
	
}