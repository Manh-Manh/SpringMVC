//package com.manhdn.oauth;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
//
//import com.manhdn.entity.userEntity;
//import com.manhdn.service.userService;
//@EnableWebSecurity
//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter{
//	@Autowired
//	userService userDetailsService;
//	@Override
//	protected void configure(HttpSecurity http) throws Exception {
//		
//		http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasAnyRole("ADMIN")
//		.anyRequest().permitAll().and().formLogin().loginPage("/app-view/login")
//		.loginProcessingUrl("/app-view/login")
//		.usernameParameter("userName").passwordParameter("password")
//		.defaultSuccessUrl("/app-view/homePage")
//		.failureUrl("/app-view/login")
//		.and().exceptionHandling().accessDeniedPage("/app-view/login");
//		
//	}
//	
//	@Override
//	public void configure(WebSecurity web) throws Exception {
//		web.ignoring().antMatchers("/asset/**");
//	}
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.userDetailsService(userDetailsService);
//	}
//}
