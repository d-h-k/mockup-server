package BanpoXi.Dong.service;

import BanpoXi.Dong.domain.Member;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

import BanpoXi.Dong.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


//테스트를 만들어야 할 원래의 로직에서
//  : CMD + Shift + T  >> 버튼을 눌러 테스트케이스를 쉽게 생성할 수 있음


class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepo;


    @BeforeEach
    public void beforeeach() {
        //  테스트는 독립적으로 실행되야하기 때문에 실행시마다 매번 만들어
        memberRepo = new MemoryMemberRepository();
        memberService = new MemberService(memberRepo);// 맴버서비스 입장에서 보면 내가 직접 new 하지않고 인스턴스 주입해줌
        //이것이 DI (디펜던시 인젝션) >>

    }

    @AfterEach
    public void afterEach() {
        memberRepo.clearStore();
    }


    @Test
    void 회원가입() {
        //실제 동작코드는 한글로 이름 적는게 안좋지만
        //테스트코드는 영어권 사람들과 일하지 않는이상 테스트코드의 함수명은 한글로 적어도 좋다!

        //추천하는 문법
        //  : given > when > then : 일단 이 패턴으로 하면서 점점 더 변형해서 나름의 방식으로 업그레이드 하는것을 추천합니다.
        //
        //  given : 이 데이터를 기반으로 하는구나
        //  when : 이런 상황을 검증하는구나


        //given : 이 데이터를 기반으로 하는구나
        Member member = new Member();
        member.setName("hello");

        //when : 이런 상황을 검증하는구나
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }//정상 flow 시 회원이름이 잘 저장되는지 확인하는 테스트케이스


    //테스트는 정상플로도 중요하지만 예외적인 상황의 테스트가 정말 중요하단다
    @Test
    public void 중복회원예외케이스(){
        //given
        Member member1 = new Member();
        member1.setName("mini");

        Member member2 = new Member();
        member2.setName("mini");

        //when
        //then
        memberService.join(member1);

        try {
            memberService.join(member2);
            fail(); //  동일한 이름의 맴버 두번째를 추가하면 fail 이 발생해야 합니다.
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다");
        }

        memberService.join(member1);
        //assertThat(IllegalStateException.class,()) -> memberService.join(member2);


    }


    @Test
    public void 중복회원예외케이스_v2_테스트케이스리팩토(){
        //given
        Member member1 = new Member();
        member1.setName("mini");

        Member member2 = new Member();
        member2.setName("mini");

        //when
        memberService.join(member1);
        //IllegalStateException e = assertThat(IllegalStateException.class, () -> memberService);


        //assertThat(IllegalStateException.class,() -> memberService.join(member2));

        //then



    }



    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}