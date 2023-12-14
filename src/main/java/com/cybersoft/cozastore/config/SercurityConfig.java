/*
package com.cybersoft.cozastore.config;

import com.cybersoft.cozastore.filter.JwtFilter;
import com.cybersoft.cozastore.provider.CustomAuthenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SercurityConfig {

    private final CustomAuthenProvider customAuthenProvider;
    private final JwtFilter jwtFilter;

    @Autowired
    public SercurityConfig(CustomAuthenProvider customAuthenProvider, final JwtFilter jwtFilter) {
        this.customAuthenProvider = customAuthenProvider;
        this.jwtFilter = jwtFilter;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder().encode("123"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder().encode("123"))
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(admin, user);
//    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .authenticationProvider(customAuthenProvider)
                .build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.csrf().disable()
                .sessionManagement().sessionCreationPolicy((SessionCreationPolicy.STATELESS))
                .and()
                .authorizeRequests()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/file/**").permitAll()
                .antMatchers(HttpMethod.GET,"/product").permitAll()
                .antMatchers(HttpMethod.POST,"/product").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/product").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET,"/cart").hasRole("USER")
                .anyRequest().authenticated()
                .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
}
*/
