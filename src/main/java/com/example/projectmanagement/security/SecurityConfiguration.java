package com.example.projectmanagement.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    BCryptPasswordEncoder bCryptEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .usersByUsernameQuery("select username, password, enabled "+
                        "from user_accounts where username = ?")
                .authoritiesByUsernameQuery("select username, role "+
                        "from user_accounts where username = ?")
                .dataSource(dataSource)
                .passwordEncoder(bCryptEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/projects/new").hasRole("ADMIN")
                .mvcMatchers("/projects/save").hasRole("ADMIN")
                .mvcMatchers("/employees/new").hasRole("ADMIN")
//                .mvcMatchers("/employees/save").hasRole("ADMIN")
                .mvcMatchers("/", "/**").permitAll()
            .and()
                .formLogin();

        //csrf disable api methods except get without csrf token in header
        //so for testing we have to disable csrf or add csrf token in header
        http.csrf().disable();
    }
}
