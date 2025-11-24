package com.clairesense.app.repository;

import com.clairesense.app.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface FileRepository  extends JpaRepository<FileEntity, Long> {
    Optional<FileEntity> findByFilePath(String filePath);
}
