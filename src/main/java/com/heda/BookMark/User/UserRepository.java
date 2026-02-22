package com.heda.BookMark.User;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
  /*  
    회원가입
        service 단에서 save()를 사용
    조회
        service 단에서 findAll(), findById() 등을 사용
    수정
        service 단에서 save() 사용
    탈퇴
        service 단에서 delete() 사용
   */

    // email로 검색할 경우 : email이 없을시 optional로 nullpointerException 방지
    Optional<UserEntity> findByEmail(String email);

}
