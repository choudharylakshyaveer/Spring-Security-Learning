package com.spring.security.learning.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

import javax.sql.DataSource;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http.authorizeHttpRequests(
        (requests) ->
            requests
                .requestMatchers("/myAccount", "/myLoans", "/myCards", "/myBalance")
                .authenticated()
                .requestMatchers("/contact", "/notices", "/error")
                .permitAll());
    http.formLogin(withDefaults());
    // http.formLogin(AbstractHttpConfigurer::disable)
    http.httpBasic(withDefaults());
    return http.build();
  }

  @Bean
  public UserDetailsService userDetailsService(DataSource dataSource) {
    /*        UserDetails user = User.withUsername("user").password("{noop}123").authorities("read").build();
    UserDetails admin = User.withUsername("admin").password("{noop}123").authorities("read").build();
    UserDetails lvs_user = User.withUsername("lvs").password("{noop}123").authorities("read").build();*/
/*
    //Below is to use InMemoryUserDetailsManager
    // For below UserDetails password is encoded from https://bcrypt-generator.com/
    UserDetails user =
        User.withUsername("user")
            .password("{bcrypt}$2a$12$WovTznOR8QYHHScJ2Fsr6.eBV5IzEyE0WTod2DR7PsokGeX0lxdjK") //123
            .authorities("read")
            .build();
    UserDetails admin =
        User.withUsername("admin")
            .password("{bcrypt}$2a$12$WovTznOR8QYHHScJ2Fsr6.eBV5IzEyE0WTod2DR7PsokGeX0lxdjK")
            .authorities("read")
            .build();
    UserDetails lvs_user =
        User.withUsername("lvs")
            .password("{bcrypt}$2a$12$WovTznOR8QYHHScJ2Fsr6.eBV5IzEyE0WTod2DR7PsokGeX0lxdjK")
            .authorities("read")
            .build();
    return new InMemoryUserDetailsManager(user, admin, lvs_user);*/
    //user : lvs password : 123
    return new JdbcUserDetailsManager(dataSource);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

   /*
   * Introduce after spring security 6.3
   */
  //@Bean //commenting as we need not to use for testing/development
  public CompromisedPasswordChecker compromisedPasswordChecker(){
    return new HaveIBeenPwnedRestApiPasswordChecker(); //It will ensure you are using strong password. Introduce after spring security 6.3
  }
}
