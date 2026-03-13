package com.heda.BookMark.domain.user.dto;

import com.heda.BookMark.domain.user.entity.LoginType;
import com.heda.BookMark.domain.user.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.LocalDateTime;

public class UserUpdateRequest {

    private String name;
    private String email;



}
