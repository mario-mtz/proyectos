/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dish.portafolio;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @version 1.0
 * @author mariomtz
 * Configuración de la seguridad del proyecto
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {
        auth
          .inMemoryAuthentication()
          .withUser("visor")
            .password(passwordEncoder().encode("visor"))
            .roles("VISOR")
            .and()
          .withUser("admin")
            .password(passwordEncoder().encode("admin"))
            .roles( "ADMIN");
    }
 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
          .authorizeRequests()
                .antMatchers("/css/**", "/login.html", "/login", "/api/proyectos").permitAll()
                .antMatchers("/proyecto", "/proyecto/consultar/**").hasAnyRole("ADMIN", "VISOR")
                .antMatchers("/proyecto/**", "/api/auth/**").hasAnyRole("ADMIN")
          .anyRequest()
          .authenticated()
          .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/proyecto")
          .and()
                .logout()
                .logoutSuccessUrl("/login")
          .and()
          .httpBasic();
    }
    
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
