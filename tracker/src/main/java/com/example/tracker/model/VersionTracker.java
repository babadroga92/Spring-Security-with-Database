package com.example.tracker.model;

import com.example.tracker.enums.ValidStatuses;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "versiontracker")
public class VersionTracker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //should be AUTO
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ValidStatuses validStatuses;
    @Column(name = "releaseDate")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate releaseDate;
    @Column(name = "createdAt")
    private LocalDate createdAt;
    @Column(name = "lastUpdateAt")
    private LocalDate lastUpdateAt;

    public VersionTracker() {
    }

    public VersionTracker(int id, String name, String description, ValidStatuses validStatuses, LocalDate releaseDate, LocalDate createdAt, LocalDate lastUpdateAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.validStatuses = validStatuses;
        this.releaseDate = releaseDate;
        this.createdAt = createdAt;
        this.lastUpdateAt = lastUpdateAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ValidStatuses getValidStatuses() {
        return validStatuses;
    }

    public void setValidStatuses(ValidStatuses validStatuses) {
        this.validStatuses = validStatuses;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getLastUpdateAt() {
        return lastUpdateAt;
    }

    public void setLastUpdateAt(LocalDate lastUpdateAt) {
        this.lastUpdateAt = lastUpdateAt;
    }

    @Override
    public String toString() {
        return "VersionTracker{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", validStatuses=" + validStatuses +
                ", releaseDate=" + releaseDate +
                ", createdAt=" + createdAt +
                ", lastUpdateAt=" + lastUpdateAt +
                '}';
    }
    @PrePersist
    public void fillUpCreatedApp(){
        this.setCreatedAt(LocalDate.now());
    }
    @PreUpdate
    public void fillUpUpdatedAt(){
        this.lastUpdateAt = LocalDate.now();
    }
}
