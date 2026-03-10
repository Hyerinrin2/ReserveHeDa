package com.heda.BookMark.domain.user.service;

import com.heda.BookMark.domain.user.dto.UserDto;
import com.heda.BookMark.domain.user.entity.LoginType;
import com.heda.BookMark.domain.user.entity.Role;
import com.heda.BookMark.domain.user.entity.UserEntity;
import com.heda.BookMark.domain.user.repository.UserRepository;
import com.heda.BookMark.global.error.CustomErrorHandler;
import com.heda.BookMark.global.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    /* 가입
    *   가입 전 이미 가입한 이력이 있는지 확인하고 가입 여부 확인
    *   로컬 회원가입인지, 소셜 로그인인지 구분필요
    */
    public UserDto join(UserDto userdto) {
        String email;
        String displayname;

        validDuplicate(userdto.getEmail(), userdto.getName()); //1. 이메일 중복체크


        // 필수 입력 항목 입력 완료 체크
        if(userdto.getEmail()!=null&& userdto.getName()!=null){
            email = userdto.getEmail();
            displayname = userdto.getName();
        } else {
            throw new CustomErrorHandler(ErrorCode.INVALID_INPUT); // 필요입력 항목 입력하라고 에러를 던지고 싶음...
        }


        String encodePassword = passwordEncoder.encode(userdto.getPassword()); //비밀번호 암호화

        userdto.setEmail(email);
        userdto.setName(displayname);
        userdto.setPassword(encodePassword);
        userdto.setCreatedAt(LocalDateTime.now());
        userdto.setLoginType(LoginType.LOCAL);
        userdto.setRole(Role.USER);


        UserEntity saved = userRepository.save(userdto.toEntity());

        return UserDto.fromEntity(saved);
    }

    // 전체 조회 -
    public List<UserDto> findAll() {

        return userRepository.findAll().stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    // 단건 조회
    public UserDto findById(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new CustomErrorHandler(ErrorCode.USER_NOT_FOUND)); // 없으면 에러

        return UserDto.fromEntity(user); // 있으면 DTO로 변환해서 반환
    }

    // 수정
    @Transactional
    public UserDto update(Long id, UserDto userDto) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new CustomErrorHandler(ErrorCode.USER_NOT_FOUND));

        String encodedPassword = userDto.getPassword() != null
                ? passwordEncoder.encode(userDto.getPassword())
                : null;

        user.update(userDto.getName(), encodedPassword);

        return UserDto.fromEntity(user);
    }

    // 삭제 (소프트 딜리트)
    @Transactional
    public void delete(Long id) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new CustomErrorHandler(ErrorCode.USER_NOT_FOUND));

        if (user.isDeleted()) {
            throw new CustomErrorHandler(ErrorCode.USER_NOT_FOUND);
        }

        user.softDelete();
    }

    //중복 체크 메소드
    private void validDuplicate(String email, String name) {
        if (userRepository.existsByEmail(email)) {
            throw new CustomErrorHandler(ErrorCode.DUPLICATED_EMAIL);
        }
    }
}
