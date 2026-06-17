package de.mtala.todomanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TodoRequest {
    @NotBlank(message = "Todo title can not be blank")
    @Size(min = 3, max = 255, message = "Todo title should be between 3 and 255 characters")
    private String title;
    @NotBlank(message = "Todo description can not be blank")
    private String description;
}
