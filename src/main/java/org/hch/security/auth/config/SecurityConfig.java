package org.hch.security.auth.config;

import org.hch.security.auth.handler.CustomAuthFailureHandler;
import org.hch.security.auth.handler.CustomAuthSuccessHandler;
import org.hch.security.auth.model.ERole;
import org.hch.security.auth.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService)
			.passwordEncoder(bCryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
					.antMatchers("/", "/login", "/registration", "/generalForum").permitAll()
					.antMatchers("/admin").hasAuthority(ERole.ADMIN.getValue())
					.antMatchers("/user").hasAuthority(ERole.MANAGER.getValue())
					.antMatchers("/guest").hasAuthority(ERole.GUEST.getValue())
					.anyRequest().authenticated()
					.and()
				.csrf()
					.disable()
				.headers()
					.frameOptions().disable()
					.and()
				.formLogin()
					.loginPage("/login")
					.failureUrl("/login?error=true")
					.defaultSuccessUrl("/home")
					.successHandler(successHandler())
					.failureHandler(failureHandler())
					.usernameParameter("username")
					.passwordParameter("password")
					.and()
				.logout()
					.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
					.logoutSuccessUrl("/login")
					.and()
				.exceptionHandling()
					.accessDeniedPage("/access-denied");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
	    web.ignoring()
	       .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/img/**", "/scss/**", "/vendor/**");
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Bean
	public AuthenticationSuccessHandler successHandler() {
		return new CustomAuthSuccessHandler();
	}
	
	@Bean
	public AuthenticationFailureHandler failureHandler() {
		return new CustomAuthFailureHandler();
	}

}
