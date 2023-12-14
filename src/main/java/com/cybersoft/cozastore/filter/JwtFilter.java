/*
package com.cybersoft.cozastore.filter;

import com.cybersoft.cozastore.util.JWTHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Service
public class JwtFilter extends OncePerRequestFilter {

    private final JWTHelper jwtHelper;
    private final Gson gson;

    @Autowired
    public JwtFilter(final JWTHelper jwtHelper, final Gson gson) {
        this.jwtHelper = jwtHelper;
        this.gson = gson;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String headerValue = request.getHeader("Authorization");
        if (headerValue != null && headerValue.startsWith("Bearer ")){
            String token = headerValue.substring(7);
            String data = jwtHelper.parserToken(token);
            System.out.println("check "+data);
            if (data != null && !data.isEmpty()){
                Type listType = new TypeToken<ArrayList<SimpleGrantedAuthority>>(){}.getType();
                List<SimpleGrantedAuthority> roles = gson.fromJson(data, listType);
//                List<GrantedAuthority> roleEntityList = new ArrayList<>();
//                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_ADMIN");
//                roleEntityList.add(grantedAuthority);
                UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(
                        "", "", roles);
                SecurityContext context = SecurityContextHolder.getContext();
                context.setAuthentication(user);
            }
        }else {
            System.out.println("invalid");
        }

        filterChain.doFilter(request, response);
    }
}
*/
