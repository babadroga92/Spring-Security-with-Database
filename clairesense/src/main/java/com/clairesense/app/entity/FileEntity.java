package com.clairesense.app.entity;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "files")
public class FileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fileName;
    private String filePath;
    @Column(columnDefinition = "TEXT")
    private String content; //storing file text
    @OneToMany(
            mappedBy = "file",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<FileChunk> chunks = new ArrayList<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public FileEntity() {
    }

    public FileEntity(String fileName, String filePath, String content) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.content = content;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<FileChunk> getChunks() {
        return chunks;
    }

    public void setChunks(List<FileChunk> chunks) {
        this.chunks = chunks;
    }
}
