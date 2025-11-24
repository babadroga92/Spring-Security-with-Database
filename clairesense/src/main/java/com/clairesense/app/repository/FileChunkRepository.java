package com.clairesense.app.repository;

import com.clairesense.app.entity.FileChunk;
import com.clairesense.app.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileChunkRepository extends JpaRepository<FileChunk, Long> {
    List<FileChunk> findByFile(FileEntity file);

    void deleteByFile(FileEntity file);
}
