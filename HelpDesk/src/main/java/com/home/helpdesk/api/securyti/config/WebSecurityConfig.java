package com.home.helpdesk.api.securyti.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.home.helpdesk.api.securyti.jwt.JwtAuthenticationTokenFilter;
import com.home.helpdesk.api.securyti.jwt.JwtAutheticationEntryPoint;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig  extends WebSecurityConfigurerAdapter{

	@Autowired
	private JwtAutheticationEntryPoint anauthorizedHandle;
	
	@SuppressWarnings("unused")
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Override
	@Autowired
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userDetailsService()).passwordEncoder(getPasswordEncoder());
	}


	@Bean(name="encoder")
	private BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception{
		return new JwtAuthenticationTokenFilter();
	}
	
	@Bean
	protected void configure(HttpSecurity httpSecurity) throws Exception{
		httpSecurity.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(anauthorizedHandle).and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
			.antMatchers(
					HttpMethod.GET,
					"/",
					"/*.html",
					"/favicon.ico",
					"/**/*.html",
					"/**/*.css",
					"/**/*.js"
			).permitAll()
			.antMatchers("/api/auth/**").permitAll()
			.anyRequest().authenticated();
		httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		httpSecurity.headers().cacheControl();
			
			
			
			
	}
}
