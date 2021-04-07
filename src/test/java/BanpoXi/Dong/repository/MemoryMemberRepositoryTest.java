package BanpoXi.Dong.repository;

import BanpoXi.Dong.domain.Member;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
class MemoryMemberRepositoryTest {
//이제부터는 테스트를 스프링이랑 엮어서 함

    MemoryMemberRepository repo = new MemoryMemberRepository();

    @AfterEach
    public void afterEachMySequence() {
        repo.clearStore();
    }



    @Test
    public void save() {
        Member member = new Member();
        member.setName("spting");

        repo.save(member);
        Member result = repo.findById(member.getId()).get();
        //Assertions.assertEquals(member,result);

        assertThat(member).isEqualTo(result);
        //assertThat(member).isEqualTo(null);
    }

    @Test
    public void findByName() {
        Member m1 = new Member();
        m1.setName("muyaho");
        repo.save(m1);

        Member m2 = new Member();
        m2.setName("terra");
        repo.save(m2);

        Optional<Member> result = repo.findByName("muyaho");
        Member resultFinal = result.get();
        assertThat(resultFinal).isEqualTo(m1);

        Member resultM2 = repo.findByName("terra").get();
        assertThat(resultM2).isEqualTo(m2);

    }



    @Test
    public void findAll() {
        Member m1 = new Member();
        m1.setName("spring1");
        repo.save(m1);

        Member m2 = new Member();
        m2.setName("spring2");
        repo.save(m2);


        Member m3 = new Member();
        m3.setName("MuYahoff");
        repo.save(m3);

        List<Member> result = repo.findAll();
        assertThat(result.size()).isEqualTo(3);
    }
}

// 모든 테스트는 순서에 의존적으로 설계하면 절대안되
// 모든 테스트가 독립적으로 돌도록 만들어야해!!!
// 테스트가 끝나면 데이터를 클리어해줘야
// 테스트케이스 먼저 만들고 소스를 짜는게 바로 TDD
