package com.cybersoft.cozastore.provider;

import com.cybersoft.cozastore.entity.UserEntity;
import com.cybersoft.cozastore.exception.UserNotFoundException;
import com.cybersoft.cozastore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Autowired
    @Lazy
    public CustomAuthenProvider(final UserRepository userRepository, final  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        UserEntity user = userRepository.findOneByEmail(username);
        if (user != null){
            if (passwordEncoder.matches(password, user.getPassWord())){
                List<GrantedAuthority> roleEntityList = new ArrayList<>();
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().getName());
                roleEntityList.add(grantedAuthority);
                UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                        username, user.getPassWord(), roleEntityList
                );
                SecurityContextHolder.getContext().setAuthentication(token);
                return token;
            }else {
                throw new UserNotFoundException(404, "User not found", null);
            }
        }else {
            throw new UserNotFoundException(404, "User not found", null);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
