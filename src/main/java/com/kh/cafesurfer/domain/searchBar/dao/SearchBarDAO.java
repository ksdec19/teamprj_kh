package com.kh.cafesurfer.domain.searchBar.dao;


import com.kh.cafesurfer.domain.searchBar.SearchBar;

import java.util.List;

public interface SearchBarDAO {

  // 해시태그 목록 출력
  List<SearchBar> hashTagAll();           // 전체(지역 제외)
  List<SearchBar> coffeeHashTagAll();     // 커피
  List<SearchBar> beverageHashTagAll();   // 음료
  List<SearchBar> dessertHashTagAll();    // 디저트
  List<SearchBar> goodsHashTagAll();      // 굿즈
  List<SearchBar> moodHashTagAll();       // 분위기
  List<SearchBar> viewHashTagAll();       // 풍경
  List<SearchBar> locHashTagAll();        // 지역

  Long plusHahTagSearchCount(Long hashtagId);

  Long minusHahTagSearchCount(Long hashtagId);
}
