package com.cybersoft.cozastore.service.impl;

import com.cybersoft.cozastore.converter.UserConverter;
import com.cybersoft.cozastore.dto.UserDTO;
import com.cybersoft.cozastore.entity.RoleEntity;
import com.cybersoft.cozastore.entity.UserEntity;
import com.cybersoft.cozastore.repository.RoleRepository;
import com.cybersoft.cozastore.repository.UserRepository;
import com.cybersoft.cozastore.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(final UserRepository userRepository,
                        final UserConverter userConverter,
                        final RoleRepository roleRepository,
                        final PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDTO upsert(UserDTO userDTO) {
        UserEntity userEntity = userConverter.toUserEntity(userDTO);
        RoleEntity roleEntity = roleRepository.findOneById(userDTO.getRoleId());
        userEntity.setRole(roleEntity);
        userEntity.setPassWord(passwordEncoder.encode(userDTO.getPassWord()));
        return userConverter.toUserDTO(userRepository.save(userEntity));
    }
}
