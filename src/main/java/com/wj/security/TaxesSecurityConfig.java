package com.wj.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class TaxesSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		/*
		 * auth.inMemoryAuthentication().withUser("louis").password("1234").roles("USER");
		 * auth.inMemoryAuthentication().withUser("woodson").password("1234").roles("USER", "ADMIN");
		 */
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select username as principal, pass as credential, active from users where username=?")
			.authoritiesByUsernameQuery("select username as principal, role as role from users_roles where username=?")
			.passwordEncoder(new Md5PasswordEncoder())
			.rolePrefix("ROLE_");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.formLogin().loginPage("/login");
		
		/*
		 * Interdire toutes les requêtes : 
		 * toutes les requêtes neccessites une authentification
		 */
		//http.authorizeRequests().anyRequest().authenticated();
		
		http.authorizeRequests().antMatchers( "/entreprises", "/taxes").hasRole("USER");
		http.authorizeRequests().antMatchers("/formEntreprise", "/saveEntreprise").hasRole("ADMIN");
	
		http.exceptionHandling().accessDeniedPage("/403");
	
	}

	
}
