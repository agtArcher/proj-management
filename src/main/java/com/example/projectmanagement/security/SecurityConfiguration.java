package com.example.projectmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .withDefaultSchema()
                .withUser("myuser")
                .password("pass")
                .roles("USER")
            .and()
                .withUser("myuser2")
                .password("password")
                .roles("USER")
            .and()
                .withUser("managerUser")
                .password("pass3")
                .roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/projects/new").hasRole("ADMIN")
                .mvcMatchers("/employees/new").hasRole("ADMIN")
                .mvcMatchers("/h2-console/**").permitAll()
                .mvcMatchers("/").authenticated().and().formLogin();

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
