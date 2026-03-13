package com.heda.BookMark.domain.user.dto;

import com.heda.BookMark.domain.user.entity.Role;
import lombok.Builder;


import java.time.LocalDateTime;

@Builder
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private LocalDateTime createdAt;
    private Role role;
}
