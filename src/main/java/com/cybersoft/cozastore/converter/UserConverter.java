package com.cybersoft.cozastore.converter;

import com.cybersoft.cozastore.dto.UserDTO;
import com.cybersoft.cozastore.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {
    public UserDTO toUserDTO(UserEntity userEntity){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(userEntity.getId());
        userDTO.setUserName(userEntity.getUserName());
        userDTO.setEmail(userEntity.getEmail());
        userDTO.setPassWord(userEntity.getPassWord());
        userDTO.setRoleId(userEntity.getRole().getId());
        userDTO.setCreateDate(userEntity.getCreateDate());
        return userDTO;
    }

    public UserEntity toUserEntity(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userDTO.getId());
        userEntity.setUserName(userDTO.getUserName());
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setPassWord(userDTO.getPassWord());
        userEntity.setCreateDate(userDTO.getCreateDate());
        return userEntity;
    }
}
