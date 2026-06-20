package de.mtala.todomanagement.service;

import de.mtala.todomanagement.dto.TodoRequest;
import de.mtala.todomanagement.dto.TodoResponse;
import de.mtala.todomanagement.entity.Todo;
import de.mtala.todomanagement.mapper.TodoMapper;
import de.mtala.todomanagement.repository.TodoRepository;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

    @Mock
    private TodoRepository todoRepository;
    @Spy
    private TodoMapper todoMapper;
    @InjectMocks
    private TodoService todoService;

    private Instant instant1;
    private Todo javaTodo, kotlinTodo;

    @BeforeEach
    void setUp() {
        instant1 = Instant.parse("2020-01-01T00:00:00.00Z");
        Instant instant2 = instant1.plusSeconds(20);
        javaTodo = new Todo(1L, "java seminar", "java for beginner", false, instant1, instant1);
        kotlinTodo = new Todo(2L, "kotlin course", "kotlin for beginner", false, instant2, instant2);
    }

    @Test
    void shouldReturnAllTodos() {
        //Given
        Mockito.when(todoRepository.findAll()).thenReturn(List.of(javaTodo, kotlinTodo));

         //When
        List<TodoResponse> result = todoService.findAllTodos();

         //Then
        assertEquals(2, result.size());
        assertEquals("java seminar", result.get(0).getTitle());
        assertEquals("kotlin course", result.get(1).getTitle());

    }

    @Test
    void givenTodoRequestWhenCreateTodoReturnValidTodo() {
        //Given
        TodoRequest todoRequest = new TodoRequest("java seminar", "java for beginner");
        Mockito.when(todoRepository.save(Mockito.any(Todo.class))).thenReturn(javaTodo);

        //When
        TodoResponse result = todoService.createTodo(todoRequest);

        //Then
        assertEquals("java seminar", result.getTitle());
        assertEquals("java for beginner", result.getDescription());
        assertFalse(result.isCompleted());
        assertEquals(instant1, result.getCreatedAt());
        assertEquals(instant1, result.getUpdatedAt());
    }
}