package com.cesmin.todo.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cesmin.todo.user.model.User;
import com.cesmin.todo.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 모든 User 목록 조회
    public List<User> findAllUsers() {

        return userRepository.findAll();
    }

    // 새로운 User 생성
    public User createUser(User userRequest) {

        if (userRepository.existsByUsername(userRequest.getUsername())) {
            throw new IllegalArgumentException("Username already exists.");
        }

        // Username 규칙 검증 (예: 최소 5자, 최대 20자)
        if (userRequest.getUsername().length() < 5 || userRequest.getUsername().length() > 20) {
            throw new IllegalArgumentException("Username must be between 5 and 20 characters.");
        }

        // Username에 알파벳과 숫자만 포함되고, 반드시 알파벳이 포함되어야 함
        // 정규식 설명: 알파벳(대소문자 구분 없이) + 숫자 허용, 알파벳이 적어도 하나는 있어야 함
        if (!userRequest.getUsername().matches("^(?=.*[a-zA-Z])[a-zA-Z0-9]+$")) {
            throw new IllegalArgumentException(
                    "Username must contain at least one letter and can only contain letters and numbers.");
        }

        return userRepository.save(userRequest);
    }

    // User 부분 업데이트 (PATCH)
    public User updateUser(Long userId, User userRequest) {

        // 해당 User가 존재하는지 확인
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            User existingUser = optionalUser.get();

            // 부분 업데이트: 필드가 null이 아닌 경우에만 업데이트
            if (userRequest.getPassword() != null) {
                existingUser.setPassword(userRequest.getPassword());
            }
            if (userRequest.getName() != null) {
                existingUser.setName(userRequest.getName());
            }
            if (userRequest.getPhone() != null) {
                existingUser.setPhone(userRequest.getPhone());
            }
            if (userRequest.getEmail() != null) {
                existingUser.setEmail(userRequest.getEmail());
            }

            // 업데이트된 User 저장
            return userRepository.save(existingUser);
        } else {

            return null; // User가 존재하지 않으면 null 반환
        }
    }

    // User 삭제
    public boolean deleteUser(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalUser.isPresent()) {
            userRepository.delete(optionalUser.get());

            return true;
        } else {

            return false;
        }
    }
}
