package com.swiftwin.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Configuration
@EnableWebSecurity
public class UserConfig extends WebSecurityConfigurerAdapter {

	@Bean
	public UserDetailsService getUserDetailsService() {
		return new CustomUserDtlsService();
	}

	@Bean    //for encrypting password 
	public BCryptPasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
			// here we use dao authentication provider bcoz we are performing operation on db so thats why it is used
		DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
		dao.setUserDetailsService(this.getUserDetailsService());
		dao.setPasswordEncoder(getPasswordEncoder());
		return dao;
	}

	@Override  //passing encrypted pass to authentication manager 
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//it is for removing the login pagee which commes by default when we use spring security
		// any user can access registration form without spring login security
		// .formLogin().loginPage("/login") when you log out it will transfer to login page not on sprinng security login page
		http.authorizeRequests().antMatchers("/user/**").hasRole("USER").antMatchers("/**").permitAll().and()
				.formLogin().loginPage("/login").loginProcessingUrl("/dologin").defaultSuccessUrl("/user/home").and()
				.csrf().disable();
	}

}

