package com.heda.BookMark.domain.user.mapper;

import com.heda.BookMark.domain.user.dto.UserJoinRequestDto;
import com.heda.BookMark.domain.user.dto.UserResponseDto;
import com.heda.BookMark.domain.user.entity.LoginType;
import com.heda.BookMark.domain.user.entity.Role;
import com.heda.BookMark.domain.user.entity.UserEntity;

public class UserMapper {

//    필요한 메서드 두 개:
//            - toEntity(UserJoinRequest) → UserEntity

    public static UserEntity toEntity(UserJoinRequestDto dto, String encodedPassword){
        return UserEntity.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .password(encodedPassword)
                .loginType(LoginType.LOCAL)
                .role(Role.USER)
                .build();
    }
//  - toResponse(UserEntity) → UserResponse(id,name,emil,create,role)
    public static UserResponseDto toResponse(UserEntity userEntity) {
        return UserResponseDto.builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .name(userEntity.getName())
                .createdAt(userEntity.getCreatedAt())
                .role(userEntity.getRole())
                .build();
    }

}
