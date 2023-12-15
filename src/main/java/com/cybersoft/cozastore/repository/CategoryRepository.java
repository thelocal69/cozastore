package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {
    CategoryEntity findOneByName(String name);

    @Query("select c.name from category c")
    List<String> getAll();
}
