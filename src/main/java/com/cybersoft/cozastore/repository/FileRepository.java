package com.cybersoft.cozastore.repository;

import com.cybersoft.cozastore.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<FileEntity, Integer> {
    FileEntity findOneByName(String fileName);
}
