package de.mtala.todomanagement.controller;

import de.mtala.todomanagement.dto.TodoRequest;
import de.mtala.todomanagement.dto.TodoResponse;
import de.mtala.todomanagement.service.TodoService;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/todos")
public class TodoController {

    private final TodoService todoService;

     @GetMapping
     ResponseEntity<List<TodoResponse>> getAllTodos() {
         return ResponseEntity.ok(todoService.findAllTodos());
     }

     @PostMapping
     ResponseEntity<TodoResponse> createTodo(@RequestBody @Valid TodoRequest todoRequest) {
         TodoResponse todoResponse = todoService.createTodo(todoRequest);
         return ResponseEntity.status(HttpStatus.CREATED).body(todoResponse);
     }
 }
