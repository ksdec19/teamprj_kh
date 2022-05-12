package com.kh.cafesurfer.domain.report.dao;

import com.kh.cafesurfer.domain.report.Report;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class ReportDAOImpl implements ReportDAO {
  
  private final JdbcTemplate jdbcTemplate;

  /**
   * 리뷰신고
   * @param reviewId
   * @param reportTypeCode
   */
  @Override
  public void insertReport(Long reviewId, int reportTypeCode) {
    StringBuffer sql = new StringBuffer();
    sql.append(" insert into report ");
    sql.append(" values(?, ?) ");

    jdbcTemplate.update(sql.toString(), reviewId, reportTypeCode);
  }
  
  /**
   * 리뷰 id별 조회
   * @param reviewId
   * @return
   */
  @Override
  public List<Report> selectOne(Long reviewId) {
    StringBuffer sql = new StringBuffer();

    sql.append("select t1.review_id, t5.shop_name, t4.member_name, t2.review_content, t3.report_type_name, t2.report_count ");
    sql.append("from report t1, review t2, report_type t3, membership t4, coffeeShop t5 ");
    sql.append("where t1.review_id = t2.review_id ");
    sql.append("and t2.shop_id = t5.shop_id ");
    sql.append("and t1.report_type_code = t3.report_type_code ");
    sql.append("and t2.member_id = t4.member_id ");
    sql.append("and review_id = ? ");

    List<Report> reports = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Report.class), reviewId);


    return reports;
  }
  
  /**
   * 전체조회
   * @return
   */
  @Override
  public List<Report> selectAll() {
    StringBuffer sql = new StringBuffer();

    sql.append("select t1.review_id, t5.shop_name, t4.member_name, t2.review_content, t3.report_type_name, t2.report_count ");
    sql.append("from report t1, review t2, report_type t3, membership t4, coffeeShop t5 ");
    sql.append("where t1.review_id = t2.review_id ");
    sql.append("and t2.shop_id = t5.shop_id ");
    sql.append("and t1.report_type_code = t3.report_type_code ");
    sql.append("and t2.member_id = t4.member_id ");
    sql.append("order by review_id asc ");

    List<Report> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Report.class));

    return list;
  }


}
