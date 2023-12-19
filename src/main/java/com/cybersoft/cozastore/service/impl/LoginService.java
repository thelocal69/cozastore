package com.cybersoft.cozastore.service.impl;

import com.cybersoft.cozastore.converter.UserConverter;
import com.cybersoft.cozastore.dto.LoginDTO;
import com.cybersoft.cozastore.dto.UserDTO;
import com.cybersoft.cozastore.entity.RoleEntity;
import com.cybersoft.cozastore.entity.UserEntity;
import com.cybersoft.cozastore.exception.PermissionDeniedException;
import com.cybersoft.cozastore.payload.ResponseToken;
import com.cybersoft.cozastore.repository.RoleRepository;
import com.cybersoft.cozastore.repository.UserRepository;
import com.cybersoft.cozastore.service.ILoginService;
import com.cybersoft.cozastore.util.JWTHelper;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.Role;
import java.util.List;

@Service
public class LoginService implements ILoginService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTHelper jwtHelper;
    private final Gson gson;

    @Autowired
    public LoginService(final UserRepository userRepository,
                        final UserConverter userConverter,
                        final RoleRepository roleRepository,
                        final PasswordEncoder passwordEncoder,
                        final AuthenticationManager authenticationManager,
                        final JWTHelper jwtHelper,
                        final Gson gson) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtHelper = jwtHelper;
        this.gson = gson;
    }

    @Override
    public UserDTO upsert(UserDTO userDTO) {
        UserEntity userEntity = userConverter.toUserEntity(userDTO);
        RoleEntity roleEntity = roleRepository.findOneById(userDTO.getRoleId());
        userEntity.setRole(roleEntity);
        userEntity.setPassWord(passwordEncoder.encode(userDTO.getPassWord()));
        return userConverter.toUserDTO(userRepository.save(userEntity));
    }

    @Override
    public String loginAdmin(LoginDTO loginDTO) {
        String checkRole = userRepository.getRoleNameByEmail(loginDTO.getEmail());
        if (checkRole.equals("ROLE_ADMIN")) {
            return token(loginDTO);
        }else {
            throw new PermissionDeniedException(403, "Permission Denied", null);
        }
    }


    public String token(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authen = new UsernamePasswordAuthenticationToken(
                loginDTO.getEmail()
                , loginDTO.getPassword()
        );
        authenticationManager.authenticate(authen);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<SimpleGrantedAuthority> roles = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
        ResponseToken responseToken = new ResponseToken();
        responseToken.setEmail(loginDTO.getEmail());
        responseToken.setData(roles);
        String jsonRole = gson.toJson(responseToken);
        return jwtHelper.generateToken(jsonRole);
    }
}
