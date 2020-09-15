package au.edu.rmit.isys3413.sefbfit.configs;

import au.edu.rmit.isys3413.sefbfit.models.Role;
import au.edu.rmit.isys3413.sefbfit.services.SecurityUserDetailsService;
import javax.naming.NoPermissionException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  private final SecurityUserDetailsService securityUserDetailsService;

  public SecurityConfig(SecurityUserDetailsService securityUserDetailsService) {
    this.securityUserDetailsService = securityUserDetailsService;
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    // for enabling h2-console frame
    http.csrf().disable();
    http.headers().frameOptions().disable();

    // secure resources
    // ADMIN
    http.authorizeRequests().antMatchers("/workouts/new", "/workouts/edit").hasAuthority(Role.ADMIN.name());

    // USER, ADMIN
    http.authorizeRequests().antMatchers("/workouts/*", "/users/*").hasAnyAuthority(Role.USER.name(), Role.ADMIN.name());

    // ALL
    http.authorizeRequests().antMatchers("/", "/workouts", "/signup", "/login", "/loginError", "/logout").permitAll();

    // login and logout
    http.formLogin().loginPage("/login").failureUrl("/loginError").defaultSuccessUrl("/workouts")
        .and().logout().logoutSuccessUrl("/").permitAll();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
    authenticationManagerBuilder.authenticationProvider(authProvider());
  }

  @Bean
  public AuthenticationProvider authProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(securityUserDetailsService);
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
