package de.mtala.todomanagement.mapper;

import de.mtala.todomanagement.dto.TodoRequest;
import de.mtala.todomanagement.dto.TodoResponse;
import de.mtala.todomanagement.entity.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {

    public TodoResponse toTodoResponse(Todo todo) {
        return TodoResponse.builder()
                .id(todo.getId())
                .title(todo.getTitle())
                .description(todo.getDescription())
                .completed(todo.isCompleted())
                .createdAt(todo.getCreatedAt())
                .updatedAt(todo.getUpdatedAt())
                .build();
    }

    public Todo fromTodoRequest(TodoRequest todoRequest) {
        return Todo.builder()
                .title(todoRequest.getTitle())
                .description(todoRequest.getDescription())
                .build();
    }
}
