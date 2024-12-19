package com.cesmin.todo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class TodoApplication implements CommandLineRunner {

	// private final TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// List<Todo> todoList = List.of(
		// new Todo("test title1", "test contents1", "Seongmin Hong"),
		// new Todo("test title2", "test contents2", "Seongmin Hong"),
		// new Todo("test title3", "test contents3", "Seongmin Hong"));

		// todoRepository.saveAll(todoList);
	}

}
