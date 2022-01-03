package com.saturdev.springsecurity.security;

import static com.saturdev.springsecurity.security.ApplicationUserPermission.*;
import static com.saturdev.springsecurity.security.ApplicationUserRole.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .antMatchers(HttpMethod.DELETE,"management/api/**").hasAuthority(COURSES_WRITE.name())
                .antMatchers(HttpMethod.POST,"management/api/**").hasAuthority(COURSES_WRITE.name())
                .antMatchers(HttpMethod.PUT,"management/api/**").hasAuthority(COURSES_WRITE.name())
                .antMatchers(HttpMethod.GET,"management/api/**").hasAnyRole(ADMIN.name(), ADMIN_TRAINEE.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails peterparker = User.builder()
                .username("peterparker")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.STUDENT.name())
                .build();

        UserDetails james = User.builder()
                .username("james")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.ADMIN.name())
                .build();

        UserDetails tom = User.builder()
                .username("tom")
                .password(passwordEncoder.encode("password"))
                .roles(ApplicationUserRole.ADMIN_TRAINEE.name())
                .build();

        return new InMemoryUserDetailsManager(
                peterparker,
                james,
                tom);
    }
}
