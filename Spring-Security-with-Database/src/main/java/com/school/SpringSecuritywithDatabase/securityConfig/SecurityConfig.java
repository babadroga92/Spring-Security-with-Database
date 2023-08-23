package com.school.SpringSecuritywithDatabase.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(encodePwd());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/api/v*/registration/**").permitAll()
                .antMatchers("/user/**", "/userApi/**").permitAll()
                .antMatchers("/coursesTaken/**").permitAll()
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/registration/**", "/success/**").permitAll()
                .antMatchers("/student/**").hasRole("STUDENT")
                .antMatchers("/courses/**").hasRole("PROFESSOR")
                .antMatchers("/professor/**", "/coursesTaught/**").hasRole("PROFESSOR")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll();
    }
    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }
}
