package com.greenfoxacademy.zerothweekproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureAuth(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
                .withUser("robika")
                .password("pass")
                .roles("USER")
                .and()
                .withUser("admin")
                .password("pass")
                .roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.GET,"/{id}/delete").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/addTodo").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/addTodo").hasRole("USER")
                .antMatchers(HttpMethod.GET,"/{id}/edit").hasRole("ADMIN")
                .antMatchers(HttpMethod.POST,"/{id}/edit").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/").fullyAuthenticated()
                .and()
                .formLogin()
                .permitAll();
    }
}