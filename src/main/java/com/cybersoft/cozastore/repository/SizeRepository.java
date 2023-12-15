package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.SizeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SizeRepository extends JpaRepository<SizeEntity, Integer> {
    SizeEntity findOneByName(String name);

    @Query("select s.name from size s")
    List<String> getAllSizeName();
}
