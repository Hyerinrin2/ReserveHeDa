package com.heda.BookMark.User;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Builder;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private Long id;
    private String name;
    private String email;
    private String password;
    private LocalDateTime createdAt;
    private LoginType loginType;
    private Role role;

    //Entity --> DTO
    public static UserDto fromEntity(UserEntity userEntity){
        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .createdAt(userEntity.getCreatedAt())
                .loginType(userEntity.getLoginType())
                .role(userEntity.getRole())
                .build();
    }
    //DTO --> Entity

    public UserEntity toEntity(){
        return UserEntity.builder()
                .id(this.id)
                .name(this.name)
                .email(this.email)
                .password(this.password)
                .createdAt(this.createdAt)
                .loginType(this.loginType)
                .role(this.role)
                .build();
    }
}
