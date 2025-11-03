package edu.causwict.restapi.entity;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    protected Long id;
    protected LocalDateTime generated;

    protected BaseEntity() {
        this.generated = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDateTime getGenerated() { return generated; }
}
