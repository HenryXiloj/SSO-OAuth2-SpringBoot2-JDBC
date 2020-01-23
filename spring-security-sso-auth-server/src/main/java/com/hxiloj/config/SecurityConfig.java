package com.hxiloj.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@Order(1)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
     

	@Autowired
	private DataSource dataSource;
	
    @Override
    protected void configure(HttpSecurity http) throws Exception { // @formatter:off
        http.requestMatchers()
            .antMatchers("/login", "/oauth/authorize")
            .and()
            .authorizeRequests()
            .anyRequest()
            .authenticated()
            .and()
            .formLogin()
            .usernameParameter("username").passwordParameter("password")
            .permitAll()
            .and().csrf().disable();
    } // @formatter:on

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception { // @formatter:off
//        auth.inMemoryAuthentication()
//            .withUser("henry")
//            .password(passwordEncoder().encode("123"))
//            .roles("USER");
    	
    	auth.jdbcAuthentication().dataSource(dataSource)
        .usersByUsernameQuery("select username, password, enabled"
            + " from users where username=?")
        .authoritiesByUsernameQuery("select username, authority "
            + "from authorities where username=?")
        .passwordEncoder(passwordEncoder());
    	
 
    	
    	
    } // @formatter:on

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    
}