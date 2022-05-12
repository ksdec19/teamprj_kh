package com.kh.cafesurfer.web;

import com.kh.cafesurfer.domain.searchBar.SearchBar;
import com.kh.cafesurfer.domain.searchBar.svc.SearchBarSVC;
import com.kh.cafesurfer.web.api.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/search")
public class SearchBarRestController {

  private final SearchBarSVC searchBarSVC;

  //검색 카운트 증가
  @PostMapping
  public void addSearchCount(@RequestBody Long hashTagId) {
    searchBarSVC.plusHahTagSearchCount(hashTagId);
  }

  //검색 카운트 감소
  @DeleteMapping
  public void subtractSearchCount(@RequestBody Long hashTagId) {
    searchBarSVC.minusHahTagSearchCount(hashTagId);
  }

  //해시태그 전체 조회 (지역 제외)
  @GetMapping("/hashtag/all")
  public ApiResult<Object> selectAll(){
    List<SearchBar> list = searchBarSVC.hashTagAll();
    return new ApiResult<>("00","success", list);
  }

  //커피 해시태그 전체 조회
  @GetMapping("/hashtag/coffee")
  public ApiResult<Object> coffeeHashTagAll(){
    List<SearchBar> list = searchBarSVC.coffeeHashTagAll();
    return new ApiResult<>("00","success", list);
  }

  //음료 해시태그 전체 조회
  @GetMapping("/hashtag/beverage")
  public ApiResult<Object> beverageHashTagAll(){
    List<SearchBar> list = searchBarSVC.beverageHashTagAll();
    return new ApiResult<>("00","success", list);
  }

  //디저트 해시태그 전체 조회
  @GetMapping("/hashtag/dessert")
  public ApiResult<Object> dessertHashTagAll(){
    List<SearchBar> list = searchBarSVC.dessertHashTagAll();
    return new ApiResult<>("00","success", list);
  }

  //굿즈 해시태그 전체 조회
  @GetMapping("/hashtag/goods")
  public ApiResult<Object> goodsHashTagAll(){
    List<SearchBar> list = searchBarSVC.goodsHashTagAll();
    return new ApiResult<>("00","success", list);
  }

  //분위기 해시태그 전체 조회
  @GetMapping("/hashtag/mood")
  public ApiResult<Object> moodHashTagAll(){
    List<SearchBar> list = searchBarSVC.moodHashTagAll();
    return new ApiResult<>("00","success", list);
  }

  //풍경 해시태그 전체 조회
  @GetMapping("/hashtag/view")
  public ApiResult<Object> viewHashTagAll(){
    List<SearchBar> list = searchBarSVC.viewHashTagAll();
    return new ApiResult<>("00","success", list);
  }

  //지역 해시태그 전체 조회
  @GetMapping("/hashtag/loc")
  public ApiResult<Object> locHashTagAll(){
    List<SearchBar> list = searchBarSVC.locHashTagAll();
    return new ApiResult<>("00","success", list);
  }

}
