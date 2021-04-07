package BanpoXi.Dong.repository;

import BanpoXi.Dong.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(Long id);

    Optional<Member> findByName(String name);

    //INSERT INTO MEMBER (ID,NAME) VALUES ( 2 , 'java' );

    List<Member> findAll();//지금까지 저장해온 모든 회원 리스트를 반환해줌
}
/*
Optional 이라는 자바 기능 공부
: findById 가 없으면 null 이 리턴되는데, 이거를  Null을 처리하는 방법 중 하나임
  - 널 그대로 반환하지 않고 Optional 이라는걸로 감싸서 반환하는게 요즘 추세야

 */
