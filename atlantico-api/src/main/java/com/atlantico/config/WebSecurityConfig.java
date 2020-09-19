package com.atlantico.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import com.atlantico.exception.CustomAccessDeniedHandler;
import com.atlantico.exception.CustomAuthenticationEntryPoint;
import com.atlantico.service.UserDetailsService;

@Configuration
@EnableWebSecurity
@Order(SecurityProperties.BASIC_AUTH_ORDER)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	
	@Autowired
	private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
 
   @Autowired
   private UserDetailsService userDetailsService;

   @Bean
   public PasswordEncoder passwordEncoder() {
       return new BCryptPasswordEncoder();
   }
   
   @Override
   protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
   }
   
   @Bean
   @Override
   public AuthenticationManager authenticationManagerBean() throws Exception {
       return super.authenticationManagerBean();
   }
   @Override
   public void configure(WebSecurity web) throws Exception {
       web.ignoring().antMatchers("/swagger-ui.html","/swagger-ui.html**", 
    		   "/swagger-resources/**", "/v2/**", "/h2", "/h2/**","/actuator",
               "/actuator/**","/**/*.css","/v2/api-docs","/**/*.png","/webjars/**","/configuration/**")
       .antMatchers(HttpMethod.OPTIONS, "*");
   }
   
   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
       			.csrf().disable()
               .authorizeRequests()
               .antMatchers("/swagger-ui.html", "/swagger-resources/**").permitAll() 
               .antMatchers("/v1.0/**").authenticated()
               .and()
               .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
               .accessDeniedHandler(new CustomAccessDeniedHandler());
   }
   
}