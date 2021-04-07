package BanpoXi.Dong;


import BanpoXi.Dong.repository.JdbcMemberRepository;
import BanpoXi.Dong.repository.JpaMemberRepository;
import BanpoXi.Dong.repository.MemberRepository;
import BanpoXi.Dong.repository.MemoryMemberRepository;
import BanpoXi.Dong.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

//자바 코드로 직접 스프링 빈 등록하는 방법
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //@Autowired
    //DataSource dataSource;
    //public SpringConfig(DataSource dataSource) {
        //this.dataSource = dataSource;
    //}
    /*
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }*/

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
        // 다른 어떤 코드도 변경하지 않은상태에서
        // 인터페이스 구현체를 만들고 확장한다음에 빈만 새로 넣어주고, 컨피큐레이션 돌려보면?!

        //return new JpaMemberRepository(em);
    }

}

//과거에는 XML 로 등록했는데, 요즘에는 xml 거의 사용하지 않음

