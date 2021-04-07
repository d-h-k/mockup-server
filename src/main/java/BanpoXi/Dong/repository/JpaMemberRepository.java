package BanpoXi.Dong.repository;

import BanpoXi.Dong.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {
    //DAO 왜있냐

    private final EntityManager em;
    //엔티디 매니저라는걸로 모든게 동작해


    public JpaMemberRepository(EntityManager em) {
        this.em = em;//스프링부트가 만들어주는거 주입받으면 된다
    }

    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }//이렇게 하면 끝. 데이터베이스 만들어서 리턴까지 다 해준다

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        // JPL이라는 객체지향 쿼리언어 쓰야함
        // :name >>  JPQL  ; 큐엘스트링 >>  jpa  에서 쓰는 sql 문 문법
        //제이피에이에서 받은 필드값을 넣을때는
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        List<Member> ret = em.createQuery("select m from Member m", Member.class).getResultList();
        return ret; // 엔티티를 대상으로 쿼리를 날림
        // 맴버 객체 자체를 조회하면 끝난다! 짱이지! (기존에는 맵핑하고 난리나는데 여거는 한줄이면 끝난다)
    }
}
