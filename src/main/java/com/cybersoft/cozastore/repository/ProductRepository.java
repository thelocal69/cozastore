package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
}
