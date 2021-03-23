package ru.otus.borodkin.elibrary.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@RequiredArgsConstructor
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers("/", "/h2-console/**").permitAll()
                .antMatchers("/rest/user/**").authenticated()
                .antMatchers(HttpMethod.GET, "/rest/authors/**").hasAnyRole("ADMIN", "READ_ALL", "READ_AND_WRITE_ALL", "READ_AUTHORS")
                .antMatchers(HttpMethod.GET, "/rest/genres/**").hasAnyRole("ADMIN", "READ_ALL", "READ_AND_WRITE_ALL", "READ_GENRES")
                .antMatchers(HttpMethod.GET, "/rest/**").hasAnyRole("ADMIN", "READ_ALL", "READ_AND_WRITE_ALL")
                .antMatchers(HttpMethod.POST, "/rest/**").hasAnyRole("ADMIN", "READ_AND_WRITE_ALL")
                .antMatchers(HttpMethod.PUT, "/rest/**").hasAnyRole("ADMIN", "READ_AND_WRITE_ALL")
                .antMatchers("/rest/**").hasRole("ADMIN")
                .anyRequest().denyAll()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling()
                .defaultAuthenticationEntryPointFor(
                        new RESTAuthenticationEntryPoint(), new AntPathRequestMatcher("/rest/**"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
}
