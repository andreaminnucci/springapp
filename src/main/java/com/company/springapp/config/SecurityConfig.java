package com.company.springapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.company.springapp.filter.JwtRequestFilter;
import com.company.springapp.service.MyUserDetailsService;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private MyUserDetailsService userService;
	
	@Autowired
	private JwtRequestFilter jwtRequestFilter;
	
	@Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService);
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
	
	//https://github.com/bezkoder/spring-boot-spring-security-jwt-authentication

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
	    source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
	    return source;
  }
// https://auth0.com/blog/implementing-jwt-authentication-on-spring-boot/
	
	@Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
		/*
		httpSecurity
			.authorizeRequests()
				.antMatchers(
					 "/registration**",
		                "/js/**",
		                "/css/**",
		                "/img/**").permitAll()
			.anyRequest().authenticated()
				.and()
					.formLogin()
						.loginPage("/login")
							.permitAll()
				.and()
					.logout()
						.invalidateHttpSession(true)
							.clearAuthentication(true)
							.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
							.logoutSuccessUrl("/login?logout")
					.permitAll()
				.and()
					.addFilter(new JWTAuthenticationFilter(authenticationManager()))
					.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
				.and()
	        		.csrf().disable();
			*/
		
		/*
		httpSecurity.authorizeRequests()
        		.antMatchers("/**")
        			.permitAll()
        				.anyRequest()
        					.anonymous()
        	.and()
        		.csrf().disable();
        		*/
		httpSecurity.csrf().disable()
		.authorizeRequests().antMatchers("/authenticate").permitAll().
				anyRequest().authenticated().and().
				exceptionHandling().and().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			
		httpSecurity.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
