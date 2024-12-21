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
				new Todo("Chillin", "감자네 집에서 chillin 하기~", "Seongmin Hong"),
				new Todo("화장실 청소", "변기, 거울 청소", "Seongmin Hong"),
				new Todo("감자똥꾸멍", "감자 똥구멍 불쌍해.. 방귀냄새 뒤져...", "Seongmin Hong"));

		todoRepository.saveAll(todoList);
	}

}
