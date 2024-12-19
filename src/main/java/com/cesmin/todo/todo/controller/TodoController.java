package com.cesmin.todo.todo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cesmin.todo.todo.model.Todo;
import com.cesmin.todo.todo.service.TodoService;
import com.cesmin.todo.user.model.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final TodoService todoService;

    // 전체 Todo 목록 조회
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getTodos() {

        Map<String, Object> response = new HashMap<>();

        List<Todo> todos = todoService.findAllTodos();
        response.put("message", "Todo list retrieved");
        response.put("status", HttpStatus.OK.value());
        response.put("data", todos);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 새로운 Todo 생성
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> addTodo(@RequestBody Todo todoRequest) {

        Map<String, Object> response = new HashMap<>();

        try {
            Todo createdTodo = todoService.createTodo(todoRequest);

            response.put("message", "Todo created");
            response.put("status", HttpStatus.CREATED.value());
            response.put("data", createdTodo); // 생성된 유저 정보도 응답에 포함 가능

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            response.put("message", e.getMessage());
            response.put("status", HttpStatus.BAD_REQUEST.value());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        // Todo createdTodo = todoService.createTodo(todoRequest);

        // return ResponseEntity.ok(createdTodo);
    }

    // Todo 부분 업데이트 (PATCH)
    @PatchMapping("/update/{todoId}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("todoId") Long todoId, @RequestBody Todo todoRequest) {

        Todo updatedTodo = todoService.updateTodo(todoId, todoRequest);

        if (updatedTodo != null) {

            return ResponseEntity.ok(updatedTodo);
        } else {

            return ResponseEntity.notFound().build(); // 리소스가 없으면 404 반환
        }
    }

    // Todo 삭제
    @DeleteMapping("/delete/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable("todoId") Long todoId) {

        boolean isDeleted = todoService.deleteTodo(todoId);

        if (isDeleted) {

            return ResponseEntity.noContent().build();
        } else {

            return ResponseEntity.notFound().build();
        }
    }
}
