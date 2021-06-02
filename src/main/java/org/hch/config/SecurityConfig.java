package org.hch.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/").permitAll()
		.antMatchers("/member/**").authenticated()
		.antMatchers("/admin/**").hasRole("ADMIN");
		
		http.csrf().disable();
		http.formLogin().loginPage("/login").defaultSuccessUrl("/loginSuccess",true);
	}
}
