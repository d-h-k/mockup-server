package BanpoXi.Dong.repository;

import BanpoXi.Dong.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JdbcTemplateMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    //@Autowired//스프링에서 이걸 권장함. 마이바티스같은거
    //그럼에도 불구하고 생성자가 하나면 스프링에서 빈으로 등록해주니까 그냥 오토와이어 어노테이션 안붙여도 됨
    public JdbcTemplateMemberRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        return null;
    }

    //이 JDBC 템플릿 라이브러리는 템플릿 메서드 패턴을 줄이고 줄이고 자바를 엄청나게 잘 만들어서 줄인게 결국 결과물이 이거다
    //이름이 왜 템플릿이냐면, 디자인패턴중에 템플릿 메서드 패턴이 있는게 그게 많이 들어가서 코드를 줄였거든 그래서 제이디비씨 템플릿이라고 하는거야야
   @Override
    public Optional<Member> findById(Long id) {
        List<Member> result =  jdbcTemplate.query("select * form member where id =?",memberRowMapper());
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }

    private RowMapper<Member> memberRowMapper() {
        return (rs, rowNum) -> {
            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));
            return member;
        };
    }

}
