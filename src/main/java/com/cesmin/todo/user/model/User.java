package com.cesmin.todo.user.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Title is required")
    @Size(max = 20, message = "Title cannot be longer than 20 characters")
    @Column(unique = true)
    private String username;

    @NotBlank(message = "Contents is required")
    @Size(max = 255, message = "Contents cannot be longer than 255 characters")
    private String password;

    @NotBlank(message = "Name is required")
    @Size(max = 50, message = "Name cannot be longer than 255 characters")
    private String name;

    @NotBlank(message = "Phone is required")
    @Size(max = 20, message = "Phone cannot be longer than 20 characters")
    private String phone;

    @Size(max = 50, message = "Email cannot be longer than 50 characters")
    private String email;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public User(String username, String password, String name, String phone, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.email = email;
    }
}
