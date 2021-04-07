package BanpoXi.Dong.repository;

import BanpoXi.Dong.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class JdbcMemberRepository implements MemberRepository {


    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource) {
        this.dataSource = dataSource;

    }

    @Override
    public Member save(Member member) {
        String sql = "insert into member(name) values(?)";

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;//이게 뭐냐면 결과를 받는거야

        try {
            conn = dataSource.getConnection();//커녁션 가져오기
            pstmt = conn.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);//리턴제너레이트 키는 >> 1번2번
            pstmt.setString(1, member.getName());//매칭되는 값
            pstmt.executeUpdate();//실제 쿼리가 날라가는 시점
            rs = pstmt.getGeneratedKeys();//
            if (rs.next()) {
                member.setId(rs.getLong(1));
            } else {
                throw new SQLException("id 조회 실패");
            }
            return member;
            /*
            영한님의 강의
            - 여기서 실패하면 익셉션을 미친듯이 떤진다
            - 익셉션 엄청 던지니까 트라이캐치 잘해야된다
            - 자원들 쓴것들, 릴리즈 해줘야한다
            - 외부자원들(데이터베이스들) 쓰고 꼭 전원꺼줘야한다(자원반납을 해야한다)
            - 만약 안하면 >> 큰일납니다 증말
            - 커넥션 계속쌓이다가 장애 큰장애 나니까 잘 닫아줘라


             */

        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {

            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setLong(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            } else {
                return Optional.empty();
            }
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public List<Member> findAll() {
        String sql = "select * from member";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            List<Member> members = new ArrayList<>();
            while (rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                members.add(member);
            }
            return members;
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    @Override
    public Optional<Member> findByName(String name) {
        String sql = "select * from member where name = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Member member = new Member();
                member.setId(rs.getLong("id"));
                member.setName(rs.getString("name"));
                return Optional.of(member);
            }
            return Optional.empty();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        } finally {
            close(conn, pstmt, rs);
        }
    }

    private Connection getConnection() {
        return DataSourceUtils.getConnection(dataSource);
        //참고
        // 데이터소스 유틸스를 통해서 이렇게 해야되는데 왜 이러냐면, 참고인데
        // DataSourceUtils 를 통해서 획득해야되 왜냐면 트랜잭션같은거 유지 시켜주니까
        //스프링 쓸때는 꼭 DataSourceUtils 를 쓰고, 닫을떄도 꼭 DataSourceUtils 를 이용해서 닫음
    }

    private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (conn != null) {
                close(conn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void close(Connection conn) throws SQLException {
        DataSourceUtils.releaseConnection(conn, dataSource);//닫을떄도 꼭 DataSourceUtils 필요함
    }

}
