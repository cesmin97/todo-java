package com.cesmin.todo.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cesmin.todo.todo.model.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
