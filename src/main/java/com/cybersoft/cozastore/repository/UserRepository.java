package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findOneByEmail(String email);

    @Query("SELECT  u.role.name FROM user u JOIN role r ON u.role.id = r.id WHERE u.email = ?1")
    String getRoleNameByEmail(String email);
}
