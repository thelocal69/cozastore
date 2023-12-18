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
import org.springframework.web.cors.CorsConfiguration;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAuthenProvider customAuthenProvider;
    private final JwtFilter jwtFilter;

    @Autowired
    public SecurityConfig(CustomAuthenProvider customAuthenProvider, final JwtFilter jwtFilter) {
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
        return httpSecurity.cors(cors -> cors.configurationSource(
                request -> {
                    CorsConfiguration configuration = new CorsConfiguration();
                    configuration.setAllowedOrigins(List.of("http://localhost:3000/"));
                    configuration.setAllowedHeaders(Arrays.asList("X-CSRF-Token", "X-Requested-With", "client-security-token", "Content-Type", "Accept", "Authorization"));
                    configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "DELETE"));
                    configuration.setAllowCredentials(true);
                    return  configuration;
                }
                ))
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy((SessionCreationPolicy.STATELESS))
                .and()
                .authorizeRequests()
                .antMatchers("/account/**").permitAll()
                .antMatchers("/file/**").permitAll()
                .antMatchers(HttpMethod.GET,"/product").permitAll()
                .antMatchers(HttpMethod.GET,"/product/api/getAll").permitAll()
                .antMatchers(HttpMethod.GET,"/product/api/productName").permitAll()
                .antMatchers(HttpMethod.POST,"/product/api/insert").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT,"/product/api/update/id/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE,"/product/api/delete/id/{id}").hasRole("ADMIN")
                .antMatchers(HttpMethod.GET, "/category").permitAll()
                .antMatchers(HttpMethod.GET, "/category/api/getName").permitAll()
                .antMatchers(HttpMethod.GET, "/color").permitAll()
                .antMatchers(HttpMethod.GET, "/color/api/getName").permitAll()
                .antMatchers(HttpMethod.GET, "/size").permitAll()
                .antMatchers(HttpMethod.GET, "/size/api/getName").permitAll()
                .antMatchers(HttpMethod.GET,"/cart").hasRole("USER")
                .anyRequest().authenticated()
                .and().addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
}
