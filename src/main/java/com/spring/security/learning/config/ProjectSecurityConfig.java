package com.spring.security.learning.config;

import static org.springframework.security.config.Customizer.withDefaults;

import com.spring.security.learning.exceptionhandling.CustomAccessDeniedHandler;
import com.spring.security.learning.exceptionhandling.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;

@Profile("!prod")
@Configuration
public class ProjectSecurityConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http.requiresChannel(rcc -> rcc.anyRequest().requiresInsecure()) // only http
        .authorizeHttpRequests(
            (requests) ->
                requests
                    .requestMatchers("/myAccount", "/myLoans", "/myCards", "/myBalance")
                    .authenticated()
                    .requestMatchers("/contact", "/notices", "/error", "/register")
                    .permitAll());
    http.formLogin(withDefaults());
    http.httpBasic(
        httpSecurityHttpBasicConfigurer ->
            httpSecurityHttpBasicConfigurer.authenticationEntryPoint(
                new CustomBasicAuthenticationEntryPoint()));
    http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
    http.exceptionHandling(
        exceptionHandlingConfigurer ->
            exceptionHandlingConfigurer
                .authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler()));

    return http.build();
  }

  // As we have created our own EazyBankUserDetailsService so we don't need the below code of
  // default UserDetailsService
  /*  @Bean
  public UserDetailsService userDetailsService(DataSource dataSource) {
    //user : lvs password : 123
    return new JdbcUserDetailsManager(dataSource);
  }*/

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  /*
   * Introduce after spring security 6.3
   * It will ensure you are using strong password.  Introduce after spring security 6.3
   */
  // @Bean //commenting as we need not to use for testing/development
  public CompromisedPasswordChecker compromisedPasswordChecker() {
    return new HaveIBeenPwnedRestApiPasswordChecker();
  }
}
