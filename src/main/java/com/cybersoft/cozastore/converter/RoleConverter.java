package com.cybersoft.cozastore.converter;

import com.cybersoft.cozastore.dto.RoleDTO;
import com.cybersoft.cozastore.entity.RoleEntity;
import org.springframework.stereotype.Component;

@Component
public class RoleConverter {
    public RoleDTO toRoleDTO(RoleEntity roleEntity){
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setId(roleEntity.getId());
        roleDTO.setName(roleEntity.getName());
        roleDTO.setCreateDate(roleEntity.getCreateDate());
        return roleDTO;
    }

    public RoleEntity toRoleEntity(RoleDTO roleDTO){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(roleDTO.getId());
        roleEntity.setName(roleDTO.getName());
        roleEntity.setCreateDate(roleDTO.getCreateDate());
        return roleEntity;
    }
}
