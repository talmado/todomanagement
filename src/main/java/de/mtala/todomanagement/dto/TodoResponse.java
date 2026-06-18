package de.mtala.todomanagement.dto;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TodoResponse {

    private Long id;
    private String title;
    private String description;
    private boolean completed;
    private Instant createdAt;
    private Instant updatedAt;
}
