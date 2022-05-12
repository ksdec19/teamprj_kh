package com.kh.cafesurfer.domain.bookMark.dao;

import com.kh.cafesurfer.domain.bookMark.Bookmark;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BookMarkDAOImpl implements BookMarkDAO{

  private final JdbcTemplate jdbcTemplate;

  // 찜 등록
  @Override
  public void insertBookMark(Long memberId, Long shopId ){

    StringBuffer sql = new StringBuffer();

    sql.append("insert into bookmark " );
    sql.append("values( default, ? , ?) " );

    jdbcTemplate.update(sql.toString(), memberId, shopId);
  }

  // 찜 삭제
  @Override
  public void deleteBookMark(Long memberId, Long shopId) {

    StringBuffer sql = new StringBuffer();

   sql.append(" DELETE FROM bookmark ");
   sql.append("     WHERE ");
   sql.append(" member_id = ? ");
   sql.append(" AND shop_id = ? ");

    jdbcTemplate.update(sql.toString(), memberId, shopId);
  }

  // 찜 조회 by 회원Id
  @Override
  public List<Bookmark> selectBookMarkByMemberId(Long memberId) {

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
    sql.append("      t2.hcategoryH0101, hcategoryH0102, hcategoryH0103, hcategoryH0104,");
    sql.append("      t3.UPLOADFILE_ID, ");
    sql.append("      t3.CODE, ");
    sql.append("      t3.RID, ");
    sql.append("      t3.STORE_FILENAME, ");
    sql.append("      t3.UPLOAD_FILENAME, ");
    sql.append("      t4.BOOKMARK_CHKDATE, ");
    sql.append("      t4.MEMBER_ID ");
    sql.append("  from coffeeshop t2, uploadfile t3,BOOKMARK t4 ");
    sql.append("  WHERE t2.SHOP_ID = t3.RID ");
    sql.append("  and t2.SHOP_ID =t4.shop_id ");
    sql.append("  and t2.bcategoryB0105 = t3.code ");
    sql.append("  and t4.MEMBER_ID = ? ");

    List<Bookmark> list = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(Bookmark.class), memberId);
    return list;
  }

  // 찜 카운트 증가
  @Override
  public Long plusBookmarkCnt(Long shopId) {
    StringBuffer sql = new StringBuffer();

    sql.append("update coffeeShop ");
    sql.append("set shop_bookmark_count = shop_bookmark_count + 1 ");
    sql.append("where shop_Id = ? ");

    long cnt = jdbcTemplate.update(sql.toString(), shopId);

    return cnt;
  }

  // 찜 카운트 감소
  @Override
  public Long minusBookmarkCnt(Long shopId) {
    StringBuffer sql = new StringBuffer();

    sql.append("update coffeeShop ");
    sql.append("set shop_bookmark_count = shop_bookmark_count - 1 ");
    sql.append("where shop_Id = ? ");

    long cnt = jdbcTemplate.update(sql.toString(), shopId);

    return cnt;
  }

}
