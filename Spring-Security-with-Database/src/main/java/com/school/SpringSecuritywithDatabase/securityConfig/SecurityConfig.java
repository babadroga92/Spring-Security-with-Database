package com.school.SpringSecuritywithDatabase.securityConfig;

import com.school.SpringSecuritywithDatabase.enums.Roles;
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
                .antMatchers("/image/**").permitAll()
                .antMatchers("/api/v*/registration/**").permitAll()
                .antMatchers("/user/**", "/userApi/**").permitAll()
                .antMatchers("/coursesTaken/**").permitAll()
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/registration/**", "/success/**").permitAll()
                .antMatchers("/student/**").permitAll()
                .antMatchers("/courses/**").permitAll()
                .antMatchers("/professor/**", "/coursesTaught/**").permitAll();
//                .anyRequest().authenticated()
//                .and()
////                .httpBasic();
//                .formLogin().loginPage("/login").defaultSuccessUrl("/success").permitAll();

    }
    @Bean
    public BCryptPasswordEncoder encodePwd(){
        return new BCryptPasswordEncoder();
    }
}
