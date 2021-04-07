package BanpoXi.Dong.repository;

import BanpoXi.Dong.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 스프링 데이터 JPA가 자동으로 빈으로 등록해요 >> 더 쓰기 위해서는 Spring Config
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    @Override
    Optional<Member> findByName(String name);
}
