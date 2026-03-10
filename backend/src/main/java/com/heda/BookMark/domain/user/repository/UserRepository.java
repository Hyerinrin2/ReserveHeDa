package com.heda.BookMark.domain.user.repository;

import com.heda.BookMark.domain.user.entity.UserEntity;
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

    //회원가입 시 중복여부 검사용 조회 <-- 예 아니요 만 알려주면 됨
    boolean existsByEmail(String email);
    boolean existsByName(String name);


}
