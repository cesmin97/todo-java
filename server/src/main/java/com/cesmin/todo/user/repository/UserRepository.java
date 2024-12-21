package com.cesmin.todo.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cesmin.todo.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

}
