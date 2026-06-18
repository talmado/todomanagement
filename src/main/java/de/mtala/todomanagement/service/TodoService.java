package de.mtala.todomanagement.service;

import de.mtala.todomanagement.dto.TodoRequest;
import de.mtala.todomanagement.dto.TodoResponse;
import de.mtala.todomanagement.entity.Todo;
import de.mtala.todomanagement.mapper.TodoMapper;
import de.mtala.todomanagement.repository.TodoRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TodoService {

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    @Transactional(readOnly = true)
    public List<TodoResponse> findAllTodos() {
        return todoRepository.findAll().stream().map(todoMapper::toTodoResponse).toList();
    }

    public TodoResponse createTodo(TodoRequest todoRequest) {
        Todo todo = todoMapper.fromTodoRequest(todoRequest);
        Todo savedTodo = todoRepository.save(todo);
        return todoMapper.toTodoResponse(savedTodo);
    }
}
