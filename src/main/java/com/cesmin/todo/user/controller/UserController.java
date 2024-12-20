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

import com.cesmin.todo.exception.CustomException;
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
            response.put("data", createdUser); // 생성된 유저 정보도 응답에 포함 가능

            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (CustomException e) {
            response.put("message", e.getMessage());

            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }

    // User 부분 업데이트 (PATCH)
    @PatchMapping("/update/{userId}")
    public ResponseEntity<Map<String, Object>> updateUser(@PathVariable("userId") Long userId,
            @RequestBody User userRequest) {

        Map<String, Object> response = new HashMap<>();

        try {
            User updatedUser = userService.updateUser(userId, userRequest);

            response.put("message", "User updated");
            response.put("data", updatedUser);

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (CustomException e) {
            response.put("message", e.getMessage());

            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }

    // User 삭제
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable("userId") Long userId) {

        Map<String, Object> response = new HashMap<>();

        try {
            userService.deleteUser(userId);

            response.put("message", "User deleted");

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (CustomException e) {
            response.put("message", e.getMessage());

            return new ResponseEntity<>(response, e.getHttpStatus());
        }
    }
}
