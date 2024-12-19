package com.cesmin.todo.user.controller;

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

import com.cesmin.todo.user.model.User;
import com.cesmin.todo.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 전체 User 목록 조회
    @GetMapping("/list")
    public ResponseEntity<Map<String, Object>> getUsers() {

        Map<String, Object> response = new HashMap<>();

        List<User> users = userService.findAllUsers();
        response.put("message", "User list retrieved");
        response.put("status", HttpStatus.OK.value());
        response.put("data", users);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // 새로운 User 생성
    @PostMapping("/create")
    public ResponseEntity<Map<String, Object>> addUser(@RequestBody User userRequest) {

        Map<String, Object> response = new HashMap<>();

        try {
            User createdUser = userService.createUser(userRequest);

            response.put("message", "User created");
            response.put("status", HttpStatus.CREATED.value());
            response.put("data", createdUser); // 생성된 유저 정보도 응답에 포함 가능

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (IllegalArgumentException e) {
            response.put("message", e.getMessage());
            response.put("status", HttpStatus.BAD_REQUEST.value());

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    // User 부분 업데이트 (PATCH)
    @PatchMapping("/update/{userId}")
    public ResponseEntity<User> updateUser(@PathVariable("userId") Long userId, @RequestBody User userRequest) {

        User updatedUser = userService.updateUser(userId, userRequest);

        if (updatedUser != null) {

            return ResponseEntity.ok(updatedUser);
        } else {

            return ResponseEntity.notFound().build(); // 리소스가 없으면 404 반환
        }
    }

    // User 삭제
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId) {

        boolean isDeleted = userService.deleteUser(userId);

        if (isDeleted) {

            return ResponseEntity.noContent().build();
        } else {

            return ResponseEntity.notFound().build();
        }
    }
}
