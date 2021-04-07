package BanpoXi.Dong.service;

import BanpoXi.Dong.domain.Member;
import BanpoXi.Dong.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {
//중복된 회원 어쩌고저쩌거 >> DELETE FORM MEMBER;


    @Autowired
    MemberService memberService;
    // 원래는 생성자 주입받아야 맞는데, 여기테스트는 끝나는 지점이니까 @Autowired 에 있어도 된다@!
    @Autowired
    MemberRepository memoryMemberRepository;


    // 테스트는 반복가능해야하는데,오토커밋 이냐 아니냐
    // 테스트 두번돌리먄 안됨 왜냐? 디비에 저장되버리니까까
    // 기본적으로 트랜잭션 보내는거 있어서 커밋을 따로 안보내는
    // 인서트쿼리 날리고 롤백하면? 디비에서 데이터 반영안되고 날라감!
    // @@Transactional 을 테스트에다가 붙여주면, 테스트직전에 스프링이 알아서 트랜잭션날리고 테스트 끝난담에 롤백

    //JPA 스프링만큼 공부할께 많아요! 제대로 실무에서 쓸려면 공부좀 해야되용
    // JPA 공부를 위해서 인프런 영한님 강의 ㅊㅊ

    //@Commit : 자동롤백됨
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("hellooooo");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    //현업에서 코딩시간에
    //테스트 코드 : 60~80프로
    // 프로덕트 코드 20~40
    //잘하는사람일수록 테스트코드 꼼꼼하게
    @Test
    public void 중복회원예외케이스() {
        Member member1 = new Member();
        member1.setName("MAAAAini");
        memberService.join(member1);

        Member member2 = new Member();
        member2.setName("Aniii");

        try {
            memberService.join(member2);
            //fail(); //  동일한 이름의 맴버 두번째를 추가하면 fail 이 발생해야 합니다.
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }
        //memberService.join(member1);
    }

    @Test
    public void 중복회원예외케이스_v2_테스트케이스리팩토() {
        Member member1 = new Member();
        member1.setName("mini");
        Member member2 = new Member();
        member2.setName("mini");
        memberService.join(member1);

    }

}
