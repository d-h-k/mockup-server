package BanpoXi.Dong.repository;

import BanpoXi.Dong.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> store = new HashMap<>();
    // 실무에서는 동시성문제가 있을 수 있어 HashMap이 아니라 컨커런트해쉬맵을 쓴다 알아둬라
    private static long sequence = 0L;
    // 동시성 문제 해결을 위해 어텀롱 이런걸 쓴다는

    @Override
    public Member save(Member member) {
        member.setId(++sequence);//네임은 고객이 적어주는건데 id는 시스템에서 지정해 주는 거
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        //return store.get(id);//이렇게 해도 되지만
        return Optional.ofNullable(store.get(id));
        //만약 회원이 없다면 null 이 반환될 가능성이 있어서 Optional Nullable이라는걸로 감싸서 리턴해준
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 람다식이네 ㅎㅎ.. 공부해라
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        //사실 맵인데 반환은 리스트로
        return new ArrayList<>(store.values());
        // store 라는 해쉬맵속 모든 값들이 어레이리스트로 들어감
    }

    public void clearStore() {
        store.clear();
    }
}
