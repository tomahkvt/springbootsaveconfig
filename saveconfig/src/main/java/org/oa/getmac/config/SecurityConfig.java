package org.oa.getmac.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource;

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests().antMatchers("/login").permitAll().antMatchers("/css/**").permitAll()
				.antMatchers("**/device**").access("hasRole('ROLE_ADMIN')").antMatchers("/uploadpage**")
				.access("hasRole('ROLE_ADMIN') or hasRole('ROLE_OPERATOR')").antMatchers("/console**")
				.access("hasRole('ROLE_ADMIN')").antMatchers("/marco**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/**").authenticated().and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/devicetable").usernameParameter("username").passwordParameter("password").and()
				.exceptionHandling().accessDeniedPage("/Access_Denied").and().csrf().disable();

	}

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {

		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery("select username,password, enabled from users where username=?")
				.authoritiesByUsernameQuery("select username, role from user_roles where username=?");
	}

}