package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.ColorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<ColorEntity, Integer> {
    ColorEntity findOneByName(String name);

    @Query("select c.name from color c")
    List<String> getAllColorName();
}
