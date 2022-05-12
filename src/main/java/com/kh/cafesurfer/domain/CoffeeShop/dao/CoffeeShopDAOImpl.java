package com.kh.cafesurfer.domain.CoffeeShop.dao;


import com.kh.cafesurfer.domain.CoffeeShop.CoffeeShop;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

public class CoffeeShopDAOImpl implements CoffeeShopDAO {

  private final JdbcTemplate jdbcTemplate;

  // 커피숍 등록
  @Override
  public Long insertCoffeeShop(CoffeeShop coffeeShop) {
    // SQL 작성
    StringBuffer sql = new StringBuffer();
    sql.append("insert into coffeeShop (shop_id, shop_name, shop_address, shop_tel, parking, allday,  ");
    sql.append(" bcategoryB0101, bcategoryB0102, bcategoryB0103, bcategoryB0104, bcategoryB0105, ");
    sql.append(" HCATEGORYH0101, HCATEGORYH0102, HCATEGORYH0103, HCATEGORYH0104) ");
    sql.append(" values(coffeeShop_shop_id_seq.nextval, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? ) ");

    // SQL 실행
    KeyHolder keyHolder = new GeneratedKeyHolder();
    jdbcTemplate.update(new PreparedStatementCreator() {
      @Override
      public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        PreparedStatement pstmt = con.prepareStatement(
            sql.toString(),
            new String[]{"shop_id"} //insert 후 insert 레코드 중 반환할 컬럼명, keyHolder에 저장됨.
        );
        pstmt.setString(1, coffeeShop.getShopName());
        pstmt.setString(2, coffeeShop.getShopAddress());
        pstmt.setString(3, coffeeShop.getShopTel());
        pstmt.setString(4, coffeeShop.getParking());
        pstmt.setString(5, coffeeShop.getAllday());
        pstmt.setString(6, coffeeShop.getBcategoryB0101());
        pstmt.setString(7, coffeeShop.getBcategoryB0102());
        pstmt.setString(8, coffeeShop.getBcategoryB0103());
        pstmt.setString(9, coffeeShop.getBcategoryB0104());
        pstmt.setString(10, coffeeShop.getBcategoryB0105());
        pstmt.setString(11, coffeeShop.getHcategoryh0101());
        pstmt.setString(12, coffeeShop.getHcategoryh0102());
        pstmt.setString(13, coffeeShop.getHcategoryh0103());
        pstmt.setString(14, coffeeShop.getHcategoryh0104());
        return pstmt;
      }
    }, keyHolder);

    return Long.valueOf(keyHolder.getKeys().get("shop_id").toString());
  }

  // 커피숍 전체 목록 조회
  @Override
  public List<CoffeeShop> selectAll() {
    StringBuffer sql = new StringBuffer();

    sql.append("select * ");
    sql.append("from coffeeShop ");
    sql.append(" order by shop_id desc ");

    List<CoffeeShop> list = jdbcTemplate.query(
        sql.toString(),
        new BeanPropertyRowMapper<>(CoffeeShop.class)
    );

    return list;
  }

  // 커피숍 조회 - 페이징
  @Override
  public List<CoffeeShop> selectAll(int startRec, int endRec) {
    StringBuffer sql = new StringBuffer();

    sql.append("   SELECT t1.* ");
    sql.append("   FROM ( ");
    sql.append("       SELECT ");
    sql.append("       ROW_NUMBER() OVER (ORDER BY t2.SHOP_ID DESC) no, ");
    sql.append("       t2.shop_id, t2.shop_name, t2.shop_address, t2.shop_tel, t2.view_count, ");
    sql.append("       t2.shop_bookmark_count, t2.shop_review_count, t2.parking, t2.allday, t2.shop_cdate, ");
    sql.append("       t2.bcategoryB0101, t2.bcategoryB0102, t2.bcategoryB0103, t2.bcategoryB0104, t2.bcategoryB0105, ");
    sql.append("       t2.hcategoryh0101, t2.hcategoryh0102, t2.hcategoryh0103, t2.hcategoryh0104, ");
    sql.append("       t3.UPLOADFILE_ID, ");
    sql.append("       t3.CODE, ");
    sql.append("       t3.RID, ");
    sql.append("       t3.STORE_FILENAME, ");
    sql.append("       t3.UPLOAD_FILENAME ");
    sql.append("       from coffeeshop t2, uploadfile t3 ");
    sql.append("       WHERE t2.SHOP_ID = t3.RID ");
    sql.append("       and t2.bcategoryB0105 = t3.code ");
    sql.append("   ) t1 ");
    sql.append("   where t1.no BETWEEN ? and ? ");

    List<CoffeeShop> list = jdbcTemplate.query(sql.toString(),
        new BeanPropertyRowMapper<>(CoffeeShop.class),
        startRec, endRec
    );

    return list;
  }


  // 커피숍 검색
  @Override
  public List<CoffeeShop> selectAll(CoffeeShopFilterCondition coffeeShopFilterCondition) {
    StringBuffer sql = new StringBuffer();

    sql.append(" select t5.* ");
    sql.append(" from ( SELECT distinct ");
    sql.append(" ROW_NUMBER() OVER (ORDER BY shop_id DESC) no, shop_id, shop_name, store_filename, ");
    sql.append(" hcategoryh0101, hcategoryh0102, hcategoryh0103, hcategoryh0104 ");
    sql.append(" from( ");
    sql.append("     select distinct t1.shop_id, t1.shop_name, t4.store_filename, ");
    sql.append("     t1.hcategoryh0101, t1.hcategoryh0102, t1.hcategoryh0103, t1.hcategoryh0104 ");
    sql.append("     FROM coffeeShop t1 inner join shop_hashtag t2 ");
    sql.append("     on t1.shop_id=t2.shop_id ");
    sql.append("     left outer join hashtag_classification t3 ");
    sql.append("     on t2.hashtag_id = t3.hashtag_id ");
    sql.append("     left outer join uploadfile t4 ");
    sql.append("     on t1.shop_id = t4.rid ");
    sql.append(" where t1.bcategoryB0105 = t4.code ");

    // 분류
    sql = dynamicQueryOfSelectAll(coffeeShopFilterCondition, sql);

    sql.append("  )) t5 ");
    sql.append(" where no between ? and ? ");


    List<CoffeeShop> list = null;

    // 페이징
    list = jdbcTemplate.query(
        sql.toString(),
        new BeanPropertyRowMapper<>(CoffeeShop.class),
        coffeeShopFilterCondition.getStartRec(),
        coffeeShopFilterCondition.getEndRec()
    );


    return list;
  }

  // 커피숍 개별 조회
  @Override
  public CoffeeShop selectOne(Long shopId) {

    StringBuffer sql = new StringBuffer();
    sql.append(" SELECT ");
    sql.append("    shop_id, ");
    sql.append("    shop_name, ");
    sql.append("    shop_address, ");
    sql.append("    shop_tel, ");
    sql.append("    view_count, ");
    sql.append("    shop_bookmark_count, ");
    sql.append("    shop_review_count, ");
    sql.append("    parking, ");
    sql.append("    allday, ");
    sql.append("    shop_cdate, ");
    sql.append("    bcategoryB0101, ");
    sql.append("    bcategoryB0102, ");
    sql.append("    bcategoryB0103, ");
    sql.append("    bcategoryB0104, ");
    sql.append("    bcategoryB0105,  ");
    sql.append("    hcategoryh0101,  ");
    sql.append("    hcategoryh0102,  ");
    sql.append("    hcategoryh0103,  ");
    sql.append("    hcategoryh0104  ");
    sql.append(" FROM ");
    sql.append("     coffeeShop ");
    sql.append(" where shop_id = ? ");

    CoffeeShop coffeeShopItem = null;
    try {
      coffeeShopItem = jdbcTemplate.queryForObject(
          sql.toString(),
          new BeanPropertyRowMapper<>(CoffeeShop.class),
          shopId);
    } catch (Exception e) {     // 1건을 찾지 못하면
      coffeeShopItem = null;
    }

    return coffeeShopItem;
  }

  // 커피숍 삭제
  @Override
  public int deleteCoffeeShop(Long shopId) {
    StringBuffer sql = new StringBuffer();
    sql.append(" DELETE FROM coffeeShop ");
    sql.append(" WHERE  shop_id = ? ");

    int updateItemCount = jdbcTemplate.update(sql.toString(), shopId);

    return updateItemCount;
  }

  // 커피숍 수정
  @Override
  public int updateCoffeeShop(Long shopId, CoffeeShop coffeeShop) {
    StringBuffer sql = new StringBuffer();

    sql.append(" update coffeeShop ");
    sql.append(" set  ");
    sql.append("    shop_name =?, ");
    sql.append("    shop_address =?, ");
    sql.append("    shop_tel =?, ");
    sql.append("    parking =?, ");
    sql.append("    allday =?, ");
    sql.append("    bcategoryB0101 =?, ");
    sql.append("    bcategoryB0102 =?, ");
    sql.append("    bcategoryB0103 =?, ");
    sql.append("    bcategoryB0104 =?,  ");
    sql.append("    bcategoryB0105 =?,  ");
    sql.append("    hcategoryh0101 =?,  ");
    sql.append("    hcategoryh0102 =?,  ");
    sql.append("    hcategoryh0103 =?,  ");
    sql.append("    hcategoryh0104 =?  ");
    sql.append(" where shop_id = ? ");

    int updatedItemCount = jdbcTemplate.update(
        sql.toString(),
        coffeeShop.getShopName(),
        coffeeShop.getShopAddress(),
        coffeeShop.getShopTel(),
        coffeeShop.getParking(),
        coffeeShop.getAllday(),
        coffeeShop.getBcategoryB0101(),
        coffeeShop.getBcategoryB0102(),
        coffeeShop.getBcategoryB0103(),
        coffeeShop.getBcategoryB0104(),
        coffeeShop.getBcategoryB0105(),
        coffeeShop.getHcategoryh0101(),
        coffeeShop.getHcategoryh0102(),
        coffeeShop.getHcategoryh0103(),
        coffeeShop.getHcategoryh0104(),
        shopId
    );

    return updatedItemCount;
  }


  // 등록된 커피숍 전체건수
  @Override
  public int totalCount() {

   StringBuffer sql = new StringBuffer();

    sql.append(" select count(*) ");
    sql.append(" from ( SELECT distinct ");
    sql.append(" ROW_NUMBER() OVER (ORDER BY shop_id DESC) no, shop_id, shop_name, store_filename, ");
    sql.append(" hcategoryh0101, hcategoryh0102, hcategoryh0103, hcategoryh0104 ");
    sql.append(" from( ");
    sql.append("     select distinct t1.shop_id, t1.shop_name, t4.store_filename, ");
    sql.append("     t1.hcategoryh0101, t1.hcategoryh0102, t1.hcategoryh0103, t1.hcategoryh0104 ");
    sql.append("     FROM coffeeShop t1 inner join shop_hashtag t2 ");
    sql.append("     on t1.shop_id=t2.shop_id ");
    sql.append("     left outer join hashtag_classification t3 ");
    sql.append("     on t2.hashtag_id = t3.hashtag_id ");
    sql.append("     left outer join uploadfile t4 ");
    sql.append("     on t1.shop_id = t4.rid ");
    sql.append(" where t1.bcategoryB0105 = t4.code)) t5 ");

    Integer cnt = 0;
    cnt = jdbcTemplate.queryForObject(
        sql.toString(), Integer.class
    );

    return cnt;
  }


  @Override
  public int totalCount(CoffeeShopFilterCondition coffeeShopFilterCondition) {

    StringBuffer sql = new StringBuffer();

    sql = dynamicQueryOfTotalCont(coffeeShopFilterCondition, sql);

    sql.append(" ) ");

    Integer cnt = 0;

    cnt = jdbcTemplate.queryForObject(
        sql.toString(), Integer.class
    );

    return cnt;
  }


  // 검색 메소드 (따로 분리)
  private StringBuffer dynamicQueryOfTotalCont(CoffeeShopFilterCondition coffeeShopFilterCondition, StringBuffer sql) {

    // 검색 유형
    switch (coffeeShopFilterCondition.getSearchType()){
      case "KEYWORD":     // 키워드 검색
        String keyword = coffeeShopFilterCondition.getKeyword();
        String [] token = keyword.split(" ");
        if(token.length == 1) {
          sql.append(" select count(*) from ( ");
          sql.append("     select distinct t1.shop_id ");
          sql.append("     from coffeeshop t1, shop_hashtag t2, hashtag_classification t3, uploadfile t4 ");
          sql.append("     where t2.hashtag_id = t3.hashtag_id ");
          sql.append("     and t1.shop_id = t2.shop_id ");
          sql.append("     and t1.shop_id = t4.rid ");
          sql.append("     and (t1.SHOP_NAME like '%" + token[0]+ "%' ");
          sql.append(" or t3.HASHTAG_NAME like '%" + token[0]+ "%') ");
        } else if (token.length > 1) {
          sql.append("select count(*) from ( ");

          for (int i = 0; i < token.length; i++){
            sql.append("  select distinct t1.shop_id ");
            sql.append("     from coffeeshop t1, shop_hashtag t2, hashtag_classification t3, uploadfile t4 ");
            sql.append("     where t2.hashtag_id = t3.hashtag_id ");
            sql.append("     and t1.shop_id = t2.shop_id ");
            sql.append("     and t1.shop_id = t4.rid ");
            sql.append("     and (t1.SHOP_NAME like '%" + token[i]+ "%' ");
            sql.append(" or t3.HASHTAG_NAME like '%" + token[i]+ "%') ");
            if(i != token.length-1) {
              sql.append("intersect ");
            }
          }
        }
        break;
      case "HASHTAG":     // 해시태그 검색
        keyword = coffeeShopFilterCondition.getKeyword();
        token = keyword.split(", ");
        if(token.length == 1) {
          sql.append(" select count(*) from ( ");
          sql.append("     select distinct t1.shop_id ");
          sql.append("     from coffeeshop t1, shop_hashtag t2, hashtag_classification t3, uploadfile t4 ");
          sql.append("     where t2.hashtag_id = t3.hashtag_id ");
          sql.append("     and t1.shop_id = t2.shop_id ");
          sql.append("     and t1.shop_id = t4.rid ");
          sql.append("     and t3.HASHTAG_NAME like '%" + token[0]+ "%' ");
        } else if (token.length > 1) {
          sql.append("select count(*) from ( ");

          for (int i = 0; i < token.length; i++){
            sql.append("     select distinct t1.shop_id ");
            sql.append("     from coffeeshop t1, shop_hashtag t2, hashtag_classification t3, uploadfile t4 ");
            sql.append("     where t2.hashtag_id = t3.hashtag_id ");
            sql.append("     and t1.shop_id = t2.shop_id ");
            sql.append("     and t1.shop_id = t4.rid ");
            sql.append("     and t3.HASHTAG_NAME like '%" + token[i]+ "%' ");
            if(i != token.length-1) {
              sql.append("intersect ");
            }
          }
        }
        break;
      default:
    }
    return sql;
  }

  private StringBuffer dynamicQueryOfSelectAll(CoffeeShopFilterCondition coffeeShopFilterCondition, StringBuffer sql) {

    // 검색유형
    switch (coffeeShopFilterCondition.getSearchType()){
      case "KEYWORD":         // 키워드 검색
        String keyword = coffeeShopFilterCondition.getKeyword();
        String [] token = keyword.split(" ");
        if (token.length == 1) {
          sql.append("  and (  t1.SHOP_NAME    like '%"+ token[0] +"%' ");
          sql.append("  or t3.HASHTAG_NAME like '%"+ token[0] +"%') ");
        } else if (token.length > 1) {
          for (int i = 0; i < token.length; i++) {
            sql.append("  and (  t1.SHOP_NAME    like '%"+ token[i] +"%' ");
            sql.append("    or t3.HASHTAG_NAME like '%"+ token[i] +"%') ");
          }

        }
        break;
      case "HASHTAG":       // 해시태그 검색
        keyword = coffeeShopFilterCondition.getKeyword();
        token = keyword.split(", ");
        if (token.length == 1) {
          sql.append(" and t3.HASHTAG_NAME   like '%"+ keyword +"%' ");
        } else if (token.length > 1) {
          sql.append(" and t3.HASHTAG_NAME   like '%"+ token[0] +"%' ");
          sql.append("intersect ");
          for (int i = 1; i < token.length; i++) {
            sql.append(" select distinct t1.shop_id, t1.shop_name, t4.store_filename, ");
            sql.append(" t1.hcategoryh0101, t1.hcategoryh0102, t1.hcategoryh0103, t1.hcategoryh0104  ");
            sql.append(" FROM coffeeShop t1 inner join shop_hashtag t2 ");
            sql.append(" on t1.shop_id=t2.shop_id ");
            sql.append(" left outer join hashtag_classification t3 ");
            sql.append(" on t2.hashtag_id = t3.hashtag_id ");
            sql.append(" left outer join uploadfile t4 ");
            sql.append(" on t1.shop_id = t4.rid ");
            sql.append(" where t1.bcategoryB0105 = t4.code ");
            sql.append(" and t3.HASHTAG_NAME   like '%"+ token[i] +"%' ");
            if(i != token.length-1) {
              sql.append("intersect ");
            }
          }
        }
        break;
      default:
    }
    return sql;
  }

  /**
   * 조회수 카운트
   * @param shopId
   * @return
   */
  @Override
  public Long updateViewCnt(Long shopId) {

    StringBuffer sql = new StringBuffer();

    sql.append("update coffeeShop ");
    sql.append("set view_count = view_count + 1 ");
    sql.append("where shop_id = ? ");

    long cnt = jdbcTemplate.update(sql.toString(), shopId);

    return cnt;
  }

  /**
   * 찜 카운트
   * @param shopId
   * @return
   */
  @Override
  public Long updateBookmarkCnt(Long shopId) {

    StringBuffer sql = new StringBuffer();

    sql.append("update coffeeShop ");
    sql.append("shop_bookmark_count = shop_bookmark_count + 1 ");
    sql.append("where shop_id = ? ");

    long cnt = jdbcTemplate.update(sql.toString(), shopId);

    return cnt;
  }

  /**
   * 리뷰 수 카운트
   * @param shopId
   * @return
   */
  @Override
  public Long updateShopReviewCnt(Long shopId) {

    StringBuffer sql = new StringBuffer();

    sql.append("update coffeeShop ");
    sql.append("shop_review_count = shop_review_count ");
    sql.append("where shop_Id = ? ");

    long cnt = jdbcTemplate.update(sql.toString(), shopId);

    return cnt;
  }

  // 커피숍 조회시 찜 등록여부 판단
  @Override
  public Boolean selectShopBookmark(Long shopId, Long memberId) {

    StringBuffer sql = new StringBuffer();

    sql.append("select count(member_id) ");
    sql.append("from bookmark ");
    sql.append("where shop_id = ? ");
    sql.append("and member_id = ? ");

    Integer count = jdbcTemplate.queryForObject(sql.toString(), Integer.class, shopId, memberId);

    return count == 1? true : false;
  }
}