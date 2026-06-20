package de.mtala.todomanagement.controller;

import de.mtala.todomanagement.dto.TodoRequest;
import de.mtala.todomanagement.dto.TodoResponse;
import de.mtala.todomanagement.service.TodoService;
import java.time.Instant;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureWebMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = TodoController.class)
@AutoConfigureWebMvc
class TodoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private TodoService todoService;

    private TodoRequest javaTodoRequest;
    private TodoResponse javaTodoResponse;

    @BeforeEach
    void setUp() {
        Instant instant = Instant.parse("2020-01-01T00:00:00.00Z");
        javaTodoRequest = new TodoRequest("java seminar", "java for beginner");
        javaTodoResponse = new TodoResponse(1L, "java seminar", "java for beginner", false, instant, instant);
    }

    @Test
    void whenGetAllTodos_thenReturnTodos() throws Exception {
        when(todoService.findAllTodos()).thenReturn(List.of(javaTodoResponse));
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/todos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].title").value("java seminar"))
                .andExpect(jsonPath("$[0].description").value("java for beginner"))
                .andExpect(jsonPath("$[0].completed").value(false));
    }

    @Test
    void createTodo() throws Exception {
        when(todoService.createTodo(javaTodoRequest)).thenReturn(javaTodoResponse);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/todos")
                        .content("""
                                {
                                    "title": "java seminar",
                                    "description": "java for beginner"
                                }
                                """)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.title").value("java seminar"))
                .andExpect(jsonPath("$.description").value("java for beginner"))
                .andExpect(jsonPath("$.completed").value(false));
    }
}