package com.cesmin.todo.todo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cesmin.todo.todo.model.Todo;
import com.cesmin.todo.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;

    // 모든 Todo 목록 조회
    public List<Todo> findAllTodos() {

        return todoRepository.findAll();
    }

    // 새로운 Todo 생성
    public Todo createTodo(Todo todoRequest) {

        if (todoRequest.getTitle() == null || todoRequest.getTitle().trim() == "") {
            throw new IllegalArgumentException("Title is required");
        }
        if (todoRequest.getContents() == null || todoRequest.getContents().trim() == "") {
            throw new IllegalArgumentException("Contents is required");
        }

        return todoRepository.save(todoRequest);
    }

    // Todo 부분 업데이트 (PATCH)
    public Todo updateTodo(Long todoId, Todo todoRequest) {

        // 해당 Todo가 존재하는지 확인
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);

        if (optionalTodo.isPresent()) {
            Todo existingTodo = optionalTodo.get();

            // 부분 업데이트: 필드가 null이 아닌 경우에만 업데이트
            if (todoRequest.getTitle() != null) {
                existingTodo.setTitle(todoRequest.getTitle());
            }
            if (todoRequest.getContents() != null) {
                existingTodo.setContents(todoRequest.getContents());
            }
            if (todoRequest.getAuthor() != null) {
                existingTodo.setAuthor(todoRequest.getAuthor());
            }

            // 업데이트된 Todo 저장
            return todoRepository.save(existingTodo);
        } else {

            return null; // Todo가 존재하지 않으면 null 반환
        }
    }

    // Todo 삭제
    public boolean deleteTodo(Long todoId) {
        Optional<Todo> optionalTodo = todoRepository.findById(todoId);

        if (optionalTodo.isPresent()) {
            todoRepository.delete(optionalTodo.get());

            return true;
        } else {

            return false;
        }
    }
}
