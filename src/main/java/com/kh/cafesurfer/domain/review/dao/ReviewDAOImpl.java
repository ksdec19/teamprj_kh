package com.kh.cafesurfer.domain.review.dao;

import com.kh.cafesurfer.domain.review.Review;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReviewDAOImpl implements ReviewDAO {

  private final JdbcTemplate jdbcTemplate;

  /**
   * 리뷰등록
   * @param review
   * @return
   */
  @Override
  public Review insertReview(Review review) {

    // SQL 작성
    StringBuffer sql = new StringBuffer();

    sql.append("insert into review (review_id, review_content, member_id, shop_id ) ");
    sql.append("values ( ");
    sql.append("    review_review_id_seq.nextval, ");
    sql.append("    ? , ");
    sql.append("    ?, ");
    sql.append("    ? ) ");

    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(
            sql.toString(),
            new String[]{"review_id"}
        );

        pstmt.setString(1, review.getReviewContent());
        pstmt.setLong(2, review.getMemberId());
        pstmt.setLong(3, review.getShopId());

        return pstmt;
      }
    }, keyHolder);

    long reviewId = keyHolder.getKey().longValue();
    return selectOne(reviewId);
  }

  /**
   * 리뷰 전체조회
   * @return
   */
  @Override
  public List<Review> selectAll() {

    StringBuffer sql = new StringBuffer();

    sql.append("select t1.review_id, ");
    sql.append("       t1.review_content, ");
    sql.append("       t1.review_cdate, ");
    sql.append("       t1.report_count, ");
    sql.append("       t1.member_id, ");
    sql.append("       t1.shop_id, ");
    sql.append("       t2.member_name ");
    sql.append("  from review t1, membership t2 ");
    sql.append("  where t1.member_id = t2.member_id ");
    sql.append("order by review_id desc ");

    List<Review> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Review.class));

    return list;
  }

  /**
   * 리뷰 단건조회(by reviewId)
   * @param reviewId
   * @return
   */
  @Override
  public Review selectOne(Long reviewId) {

    StringBuffer sql = new StringBuffer();

    sql.append("select review_id, ");
    sql.append("       review_content, ");
    sql.append("       review_cdate, ");
    sql.append("       report_count, ");
    sql.append("       member_id, ");
    sql.append("       shop_id ");
    sql.append("  from review ");
    sql.append(" where review_id = ? ");

    Review reviewItem = null;

    try {
      reviewItem = jdbcTemplate.queryForObject(
          sql.toString(),
          new BeanPropertyRowMapper<>(Review.class),
          reviewId
      );
    } catch (DataAccessException e) {
      reviewItem = null;
    }
    return reviewItem;
  }

  /**
   * 회원별 리뷰조회(by memberId)
   * @param memberId
   * @return
   */
  @Override
  public List<Review> selectByMemberId(Long memberId) {
    StringBuffer sql = new StringBuffer();

    sql.append("  SELECT ");
    sql.append("  t2.shop_id, ");
    sql.append("      t2.shop_name, ");
    sql.append("      t2.shop_address, ");
    sql.append("      t2.shop_tel, ");
    sql.append("      t2.view_count, ");
    sql.append("      t2.shop_bookmark_count, ");
    sql.append("      t2.shop_review_count, ");
    sql.append("      t2.parking, ");
    sql.append("      t2.allday, ");
    sql.append("      t2.shop_cdate, ");
    sql.append("      t2.bcategoryB0101, ");
    sql.append("      t3.UPLOADFILE_ID, ");
    sql.append("      t3.CODE, ");
    sql.append("      t3.RID, ");
    sql.append("      t3.STORE_FILENAME, ");
    sql.append("      t3.UPLOAD_FILENAME, ");
    sql.append("      t4.REVIEW_ID, ");
    sql.append("      t4.REVIEW_CONTENT, ");
    sql.append("      t4.REVIEW_CDATE, ");
    sql.append("      t4.REPORT_COUNT, ");
    sql.append("      t4.MEMBER_ID ");
    sql.append("  from coffeeshop t2, uploadfile t3, review t4 ");
    sql.append("  WHERE t2.SHOP_ID = t3.RID ");
    sql.append("  and t2.SHOP_ID =t4.shop_id ");
    sql.append("  and t2.bcategoryB0105 = t3.code ");
    sql.append("  and t4.MEMBER_ID = ? ");
  
    List<Review> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Review.class), memberId);
  
    return list;
  }

  /**
   * 가게별 리뷰조회(by shopId)
   * @param shopId
   * @return
   */
  @Override
  public List<Review> selectByShopId(Long shopId) {
  
    StringBuffer sql = new StringBuffer();
  
    sql.append("select t1.review_id, ");
    sql.append("       t1.review_content, ");
    sql.append("       t1.review_cdate, ");
    sql.append("       t1.report_count, ");
    sql.append("       t1.member_id, ");
    sql.append("       t1.shop_id, ");
    sql.append("       t3.member_name ");
    sql.append("  from review t1, coffeeShop t2, membership t3 ");
    sql.append(" where t1.shop_id = t2.shop_id ");
    sql.append(" and t1.member_id = t3.member_id ");
    sql.append(" and t1.shop_id = ? ");
    sql.append("order by review_id desc ");
  
    List<Review> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Review.class), shopId);
  
    return list;
  }

  /**
   * 리뷰삭제
   * @param reviewId
   * @return
   */
  @Override
  public Review deleteReview(Long reviewId) {
    
    StringBuffer sql = new StringBuffer();
  
    sql.append("delete from review ");
    sql.append("where review_id = ? ");
  
    jdbcTemplate.update(sql.toString(),
        reviewId);
  
    return null;
  }

  /**
   * 리뷰 신고당한 횟수 증가
   * @param reviewId
   * @return
   */
  @Override
  public int plusReportCnt(Long reviewId) {
    StringBuffer sql = new StringBuffer();
  
    sql.append("update review ");
    sql.append("set report_count = report_count + 1 ");
    sql.append("where review_id = ? ");
  
    int affectedRows = jdbcTemplate.update(sql.toString(), reviewId);
    
  
    return affectedRows;
  }

  /**
   * 커피숍에 등록된 리뷰 개수 증가
   * @param shopId
   * @return
   */
  @Override
  public Long plusReviewCnt(Long shopId) {
    StringBuffer sql = new StringBuffer();
  
    sql.append("update coffeeShop ");
    sql.append("set shop_review_count = shop_review_count + 1 ");
    sql.append("where shop_Id = ? ");
  
    long cnt = jdbcTemplate.update(sql.toString(), shopId);
  
    return cnt;
  }

  /**
   * 커피숍에 등록된 리뷰 개수 감소
   * @param shopId
   * @return
   */
  @Override
  public Long minusReviewCnt(Long shopId) {
    StringBuffer sql = new StringBuffer();
  
    sql.append("update coffeeShop ");
    sql.append("set shop_review_count = shop_review_count - 1 ");
    sql.append("where shop_Id = ? ");
  
    long cnt = jdbcTemplate.update(sql.toString(), shopId);
  
    return cnt;
  }

  @Override
  public List<Review> selectByReport() {

    StringBuffer sql = new StringBuffer();

    sql.append("  select * ");
    sql.append("      from review t1, coffeeShop t2, membership t3 ");
    sql.append("  where t1.shop_id = t2.shop_id ");
    sql.append("  and t1.member_id = t3.member_id ");
    sql.append("  and t1.REPORT_COUNT > 0 ");

    List<Review> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Review.class));

    return list;
  }

}
