package com.cesmin.todo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cesmin.todo.todo.model.Todo;
import com.cesmin.todo.todo.repository.TodoRepository;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class TodoApplication implements CommandLineRunner {

	private final TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Todo> todoList = List.of(
				new Todo("test title 1", "test contents 1", "Seongmin Hong"),
				new Todo("test title 2", "test contents 2", "Seongmin Hong"),
				new Todo("test title 3", "test contents 3", "Seongmin Hong"));

		todoRepository.saveAll(todoList);
	}

}
