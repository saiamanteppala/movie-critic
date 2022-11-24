/*
 * package com.javapoint.config;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.security.config.annotation.web.builders.HttpSecurity;
 * import org.springframework.security.config.annotation.web.configuration.
 * EnableWebSecurity; import
 * org.springframework.security.config.annotation.web.configuration.
 * WebSecurityConfigurerAdapter; import
 * org.springframework.security.web.util.matcher.AntPathRequestMatcher;
 * 
 * import com.javapoint.service.RegistrationService;
 * 
 * 
 * 
 * @SuppressWarnings("deprecation")
 * 
 * @Configuration
 * 
 * @EnableWebSecurity public class SecurityCofiguration extends
 * WebSecurityConfigurerAdapter {
 * 
 * @Autowired RegistrationService registrationService;
 * 
 * 
 * @Bean public AuthenticationProvider authenticationProvider() {
 * DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
 * provider.setUserDetailsService(registrationService);
 * provider.setPasswordEncoder(new BCryptPasswordEncoder()); // encrypted
 * password return provider;
 * 
 * }
 * 
 * @Override protected void configure(AuthenticationManagerBuilder provider)
 * throws Exception { provider.authenticationProvider(authenticationProvider());
 * }
 * 
 * 
 * @Override protected void configure(HttpSecurity http) throws Exception {
 * http.authorizeRequests().antMatchers("/registration**", "/js/**", "/css/**",
 * "/img/**").permitAll().anyRequest()
 * .authenticated().and().formLogin().loginPage("/login").permitAll().and().
 * logout() .invalidateHttpSession(true).clearAuthentication(true)
 * .logoutRequestMatcher(new
 * AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login?logout")
 * .permitAll(); }
 * 
 * }
 */