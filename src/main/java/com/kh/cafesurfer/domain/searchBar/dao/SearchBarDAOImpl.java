package com.kh.cafesurfer.domain.searchBar.dao;

import com.kh.cafesurfer.domain.searchBar.SearchBar;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class SearchBarDAOImpl implements SearchBarDAO{

  private final JdbcTemplate jdbcTemplate;

  // 해시태그 전체 출력 (지역 제외)
  @Override
  public List<SearchBar> hashTagAll() {
    StringBuffer sql = new StringBuffer();

    sql.append(" select * from hashtag_classification ");
    sql.append(" where not hashtag_id between 1700 and 3000 ");
    sql.append(" order by hashtag_id ");

    List<SearchBar> hashTagList = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(SearchBar.class));

    return hashTagList;
  }

  // 커피 해시태그 출력
  @Override
  public List<SearchBar> coffeeHashTagAll() {
    StringBuffer sql = new StringBuffer();

    sql.append(" select * from hashtag_classification ");
    sql.append(" where hashtag_id between 1001 and 1200 ");
    sql.append(" order by hashtag_id ");

    List<SearchBar> hashTagList = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(SearchBar.class));

    return hashTagList;
  }

  // 음료 해시태그 출력
  @Override
  public List<SearchBar> beverageHashTagAll() {
    StringBuffer sql = new StringBuffer();

    sql.append(" select * from hashtag_classification ");
    sql.append(" where hashtag_id between 1201 and 1400 ");
    sql.append(" order by hashtag_id ");

    List<SearchBar> hashTagList = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(SearchBar.class));

    return hashTagList;
  }

  // 디저트 해시태그 출력
  @Override
  public List<SearchBar> dessertHashTagAll() {
    StringBuffer sql = new StringBuffer();

    sql.append(" select * from hashtag_classification ");
    sql.append(" where hashtag_id between 1401 and 1700 ");
    sql.append(" order by hashtag_id ");

    List<SearchBar> hashTagList = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(SearchBar.class));

    return hashTagList;
  }

  // 굿즈 해시태그 출력
  @Override
  public List<SearchBar> goodsHashTagAll() {
    StringBuffer sql = new StringBuffer();

    sql.append(" select * from hashtag_classification ");
    sql.append(" where hashtag_id between 1701 and 2000 ");
    sql.append(" order by hashtag_id ");

    List<SearchBar> hashTagList = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(SearchBar.class));

    return hashTagList;
  }

  // 분위기 해시태그 출력
  @Override
  public List<SearchBar> moodHashTagAll() {
    StringBuffer sql = new StringBuffer();

    sql.append(" select * from hashtag_classification ");
    sql.append(" where hashtag_id between 3001 and 4000 ");
    sql.append(" order by hashtag_id ");

    List<SearchBar> hashTagList = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(SearchBar.class));

    return hashTagList;
  }

  // 풍경 해시태그 출력
  @Override
  public List<SearchBar> viewHashTagAll() {
    StringBuffer sql = new StringBuffer();

    sql.append(" select * from hashtag_classification ");
    sql.append(" where hashtag_id between 4001 and 5000 ");
    sql.append(" order by hashtag_id ");

    List<SearchBar> hashTagList = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(SearchBar.class));

    return hashTagList;
  }

  // 지역 해시태그 출력
  @Override
  public List<SearchBar> locHashTagAll() {
    StringBuffer sql = new StringBuffer();

    sql.append(" select * from hashtag_classification ");
    sql.append(" where hashtag_id between 2001 and 3000 ");
    sql.append(" order by hashtag_id ");

    List<SearchBar> hashTagList = jdbcTemplate.query(sql.toString(), new BeanPropertyRowMapper<>(SearchBar.class));

    return hashTagList;
  }


  @Override
  public Long plusHahTagSearchCount(Long hashtagId) {
    StringBuffer sql = new StringBuffer();

    sql.append("update hashtag_classification ");
    sql.append("set hashtag_search_count = hashtag_search_count + 1 ");
    sql.append("where hashtag_id = ? ");

    long cnt = jdbcTemplate.update(sql.toString(), hashtagId);

    return cnt;
  }

  @Override
  public Long minusHahTagSearchCount(Long hashtagId) {
    StringBuffer sql = new StringBuffer();

    sql.append("update hashtag_classification ");
    sql.append("set hashtag_search_count = hashtag_search_count - 1 ");
    sql.append("where hashtag_id = ? ");

    long cnt = jdbcTemplate.update(sql.toString(), hashtagId);

    return cnt;
  }
}
