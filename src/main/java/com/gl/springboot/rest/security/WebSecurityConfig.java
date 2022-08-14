package com.gl.springboot.rest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.gl.springboot.rest.service.UserDetailsServiceImpl;

@EnableGlobalMethodSecurity(prePostEnabled = true, proxyTargetClass = true)
@Configuration
public class WebSecurityConfig {

	@Bean
	UserDetailsService userDetailsService() {
		return new UserDetailsServiceImpl();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());

	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/h2-ui/").permitAll().antMatchers(HttpMethod.GET, "/api/employees")
				.hasAnyAuthority("USER", "ADMIN").antMatchers(HttpMethod.POST, "/api/employees")
				.hasAnyAuthority("ADMIN").antMatchers(HttpMethod.PUT, "/api/employees").hasAnyAuthority("ADMIN")
				.antMatchers(HttpMethod.DELETE, "/api/employees").hasAnyAuthority("ADMIN")
				.antMatchers(HttpMethod.POST, "/api/role").hasAnyAuthority("ADMIN")
				.antMatchers(HttpMethod.POST, "/api/user").hasAnyAuthority("ADMIN").anyRequest().authenticated().and()
				.httpBasic().and().cors().and().csrf().disable();

		http.headers().frameOptions().sameOrigin();
		http.authenticationProvider(authenticationProvider());
		return http.build();

	}

	
	  @Bean public WebSecurityCustomizer webSecurityCustomizer() { return (web) ->
	  web.ignoring().antMatchers("/images/**", "/js/**", "/webjars/**");
	  
	  }
	 
}
