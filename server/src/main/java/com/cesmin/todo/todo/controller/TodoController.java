package com.cesmin.todo.todo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesmin.todo.exception.CustomException;
import com.cesmin.todo.todo.model.Todo;
import com.cesmin.todo.todo.service.TodoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // 전체 Todo 목록 조회
    @CrossOrigin(origins = "http://http://172.30.1.99/:3000")
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getTodos() {

        Map<String, Object> response = new HashMap<>();

        List<Todo> todos = todoService.findAllTodos();
        response.put("message", "Todo list retrieved");
        response.put("data", todos);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // Todo 생성
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> addTodo(@RequestBody Todo todoRequest) {

        Map<String, Object> response = new HashMap<>();

        try {
            Todo createdTodo = todoService.createTodo(todoRequest);

            response.put("message", "Todo created");
            response.put("data", createdTodo);

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (CustomException e) {
            response.put("message", e.getMessage());

            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }

    // Todo 업데이트
    @PatchMapping("/update/{todoId}")
    public ResponseEntity<Map<String, Object>> updateTodo(@PathVariable("todoId") Long todoId,
            @RequestBody Todo todoRequest) {

        Map<String, Object> response = new HashMap<>();

        try {
            Todo updatedTodo = todoService.updateTodo(todoId, todoRequest);

            response.put("message", "Todo updated");
            response.put("data", updatedTodo);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (CustomException e) {
            response.put("message", e.getMessage());

            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }

    // Todo 삭제
    @DeleteMapping("/delete/{todoId}")
    public ResponseEntity<Map<String, Object>> deleteTodo(@PathVariable("todoId") Long todoId) {

        Map<String, Object> response = new HashMap<>();

        try {
            todoService.deleteTodo(todoId);

            response.put("message", "Todo deleted");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (CustomException e) {
            response.put("message", e.getMessage());

            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }
}
