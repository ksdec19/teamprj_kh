package com.kh.cafesurfer.domain.memberShip.dao;

import com.kh.cafesurfer.domain.memberShip.MemberShip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberShipDAOImpl implements MemberShipDAO{

  private final JdbcTemplate jdbcTemplate;

  // 등록
  @Override
  public MemberShip insertMember(MemberShip memberShip) {
    // SQL문 작성
    StringBuffer sql = new StringBuffer();
    sql.append("insert into memberShip ");
    sql.append("values(membership_member_id_seq.nextval, ?, ?, ?, ?, ?, ?) " );

    // SQL 실행
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {

        PreparedStatement pstmt = con.prepareStatement(
            sql.toString(),
            new String[] {"member_id"}    // keyHolder에 담을 테이블의 컬럼명을 지정
        );
        pstmt.setString(1,memberShip.getMemberEmail());
        pstmt.setString(2,memberShip.getMemberPasswd());
        pstmt.setString(3,memberShip.getMemberName());
        pstmt.setString(4,memberShip.getMemberGender());
        pstmt.setLong(5,memberShip.getMemberAge());
        pstmt.setString(6,memberShip.getMemberTel());


        return pstmt;
      }
    },keyHolder);

    long member_id = keyHolder.getKey().longValue();
    log.info("신규회원등록={} 후 member_id반환값={}",memberShip, keyHolder.getKey());

    return selectMemberByMemberId(member_id);
  }

  // 수정
  @Override
  public void updateMember(MemberShip memberShip) {

    StringBuffer sql = new StringBuffer();
    sql.append("update memberShip ");
    sql.append("   set  ");
    sql.append("       member_passwd = ?, ");
    sql.append("       member_name = ?, ");
    sql.append("       member_gender = ?, ");
    sql.append("       member_age = ? ");
    sql.append(" where member_email = ? ");

    jdbcTemplate.update(
        sql.toString(),
        memberShip.getMemberPasswd(),
        memberShip.getMemberName(),
        memberShip.getMemberGender(),
        memberShip.getMemberAge(),
        memberShip.getMemberEmail());
  }

  // 조회
  @Override
  public MemberShip selectMemberByEmail(String memberEmail) {
    StringBuffer sql = new StringBuffer();
    sql.append("select member_id as memberId, ");
    sql.append("       member_email, ");
    sql.append("       member_passwd, ");
    sql.append("       member_name, ");
    sql.append("       member_gender, ");
    sql.append("       member_age, ");
    sql.append("       member_tel ");
    sql.append("  from memberShip ");
    sql.append(" where member_email = ? ");

    MemberShip memberShip = jdbcTemplate.queryForObject(
        sql.toString(),
        new BeanPropertyRowMapper<>(MemberShip.class),
        memberEmail
    );
    return memberShip;
  }

  @Override
  public MemberShip selectMemberByMemberId(Long memberId) {
    StringBuffer sql = new StringBuffer();
    sql.append("select member_id as memberId, ");
    sql.append("       member_email, ");
    sql.append("       member_passwd, ");
    sql.append("       member_name, ");
    sql.append("       member_gender, ");
    sql.append("       member_age, ");
    sql.append("       member_tel ");
    sql.append("  from memberShip ");
    sql.append(" where member_id = ? ");

    MemberShip memberShip = jdbcTemplate.queryForObject(
        sql.toString(),
        new BeanPropertyRowMapper<>(MemberShip.class),
        memberId
    );
    return memberShip;
  }

  // 전체조회
  @Override
  public List<MemberShip> selectAll() {
    StringBuffer sql = new StringBuffer();

    sql.append("select member_id as memberId, ");
    sql.append("       member_email, ");
    sql.append("       member_passwd, ");
    sql.append("       member_name, ");
    sql.append("       member_gender, ");
    sql.append("       member_age, ");
    sql.append("       member_tel ");
    sql.append("  from memberShip ");
    sql.append(" order by member_id desc ");

    List<MemberShip> list = jdbcTemplate.query(
        sql.toString(),
        new BeanPropertyRowMapper<>(MemberShip.class)
    );

    return list;
  }

  // 탈퇴
  @Override
  public void deleteMember(String memberEmail) {
    StringBuffer sql = new StringBuffer();
    sql.append("delete from memberShip ");
    sql.append(" where member_email = ? ");

    jdbcTemplate.update(sql.toString(), memberEmail);
  }

  // 이메일 등록 유무 체크
  @Override
  public boolean existMemberByEmail(String memberEmail) {
    String sql = "select count(member_email) from memberShip where member_email = ? ";
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, memberEmail);

    return (count==1) ? true : false;
  }

  // 전화번호 중복 체크
  @Override
  public boolean existMemberByTel(String memberTel) {
    String sql = "select count(member_tel) from memberShip where member_tel = ? ";
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class, memberTel);

    return (count==1) ? true : false;
  }

  // 로그인 인증
  @Override
  public MemberShip login(String memberEmail, String memberPasswd) {
    StringBuffer sql = new StringBuffer();
    sql.append("select member_id as memberId, member_email, member_name ");
    sql.append("  from memberShip ");
    sql.append(" where member_email =? and member_passwd = ? ");

    List<MemberShip> list= jdbcTemplate.query(
        sql.toString(),
        new BeanPropertyRowMapper<>(MemberShip.class),
        memberEmail, memberPasswd
    );

    return list.size() == 1 ? list.get(0) : null;
  }

  // 비밀번호 일치여부 체크
  @Override
  public boolean isMember(String memberEmail, String memberPasswd) {
    StringBuffer sql  = new StringBuffer();
    sql.append("select count(*) ");
    sql.append("  from MemberShip ");
    sql.append(" where member_email = ? and member_passwd = ? ");

    Integer count = jdbcTemplate.queryForObject(
        sql.toString(), Integer.class, memberEmail, memberPasswd
    );

    return (count == 1) ? true : false;
  }

  // 이름, 전화번호로 아이디(이메일) 찾기
  @Override
  public String findEmailByTel(String memberName, String memberTel) {
    StringBuffer sql  = new StringBuffer();
    sql.append("SELECT member_email ");
    sql.append("  from MemberShip ");
    sql.append(" where member_name = ? ");
    sql.append(" and member_tel = ? ");

    List<String> result = jdbcTemplate.query(
        sql.toString(),
        new RowMapper<String>() {
          @Override
          public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getNString("member_email");
          }
        },
        memberName,
        memberTel
    );

    return (result.size() == 1) ? result.get(0) : null;
  }

  @Override
  public String findPwByEmail(String memberName, String memberTel, String memberEmail) {
    StringBuffer sql  = new StringBuffer();
    sql.append("SELECT member_passwd ");
    sql.append("  from MemberShip ");
    sql.append(" where member_name = ? ");
    sql.append(" and member_tel = ? ");
    sql.append(" and member_email = ? ");

    List<String> result = jdbcTemplate.query(
        sql.toString(),
        new RowMapper<String>() {
          @Override
          public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getNString("member_passwd");
          }
        },
        memberName,
        memberTel,
        memberEmail
    );

    return (result.size() == 1) ? result.get(0) : null;
  }
}
