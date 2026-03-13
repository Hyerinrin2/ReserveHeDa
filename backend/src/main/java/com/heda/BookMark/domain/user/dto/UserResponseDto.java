package com.heda.BookMark.domain.user.dto;

import com.heda.BookMark.domain.user.entity.LoginType;
import com.heda.BookMark.domain.user.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private Role role;
}
