package de.mtala.todomanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String title;
    private String description;
    private boolean completed;
    private Instant createdAt;
    private Instant updatedAt;

    @PrePersist
    private void prePersist() {
        if (this.createdAt == null || this.updatedAt == null) {
            Instant instant = Instant.now();
            this.createdAt = instant;
            this.updatedAt = instant;
            this.completed = false;
        }
    }

    @PreUpdate
    private void preUpdate() {
        this.updatedAt = Instant.now();
    }
}
