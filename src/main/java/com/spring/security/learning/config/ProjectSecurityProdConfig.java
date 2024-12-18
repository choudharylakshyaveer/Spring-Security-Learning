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

@Profile("prod")
@Configuration
public class ProjectSecurityProdConfig {

  @Bean
  SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
    http.sessionManagement(
            smc ->
                smc.invalidSessionUrl("/invalidSession")
                    .maximumSessions(1)
                    .maxSessionsPreventsLogin(true))
        .requiresChannel(rcc -> rcc.anyRequest().requiresSecure()) // Only HTTPS
        .authorizeHttpRequests(
            (requests) ->
                requests
                    .requestMatchers("/myAccount", "/myLoans", "/myCards", "/myBalance")
                    .authenticated()
                    .requestMatchers(
                        "/contact", "/notices", "/error", "/register", "/invalidSession")
                    .permitAll());
    http.formLogin(withDefaults());
    // http.formLogin(AbstractHttpConfigurer::disable)
    http.httpBasic(
        httpSecurityHttpBasicConfigurer ->
            httpSecurityHttpBasicConfigurer.authenticationEntryPoint(
                new CustomBasicAuthenticationEntryPoint()));
    http.csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable());
    http.exceptionHandling(
        exceptionHandlingConfigurer ->
            exceptionHandlingConfigurer
                .authenticationEntryPoint(
                    new CustomBasicAuthenticationEntryPoint()) // this wil disable the form login in
                // UI flow
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
