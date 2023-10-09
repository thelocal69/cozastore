package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findOneById(int id);
}
