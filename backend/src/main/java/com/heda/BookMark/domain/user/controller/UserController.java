package com.heda.BookMark.domain.user.controller;

import com.heda.BookMark.domain.user.dto.UserDto;
import com.heda.BookMark.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;


    //회원가입 - 요청 본문을 받아서 응답 본문을 http 201로 리턴
    @PostMapping("/api/users/join")
    public ResponseEntity<UserDto> join(@RequestBody UserDto userdto) {
        UserDto saved = userService.join(userdto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    //로그인 -

    //전체조회 - 요청본문 받아서 findall()
    @GetMapping("/api/users")
    public ResponseEntity<List<UserDto>> findAll(){

        return ResponseEntity.ok(userService.findAll());
    }

    //단건조회 - 내정보 조회
    @GetMapping("/api/users/me")
    public ResponseEntity<UserDto> find() {
        // TODO: JWT 구현 후 SecurityContext에서 userId 추출
        Long id = null;
        return ResponseEntity.ok(userService.findById(id));
    }

    //수정
    @PatchMapping("/api/users/me")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userdto){
        // TODO : jwt 구현후 SecurityContext에서id 추출
        Long id = null;
        return ResponseEntity.ok(userService.update(id,userdto));
    }

    //삭제
    @DeleteMapping("/api/users/me")
    public ResponseEntity<Void> delete() {
        // TODO : jwt 구현후 SecurityContext에서id 추출
        Long id = null;
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
