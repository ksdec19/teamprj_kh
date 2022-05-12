package com.kh.cafesurfer.domain.CoffeeShop.dao;

import com.kh.cafesurfer.domain.CoffeeShop.CoffeeShop;

import java.util.List;

public interface CoffeeShopDAO {

  // 커피숍 등록
  Long insertCoffeeShop(CoffeeShop coffeeShop);

  // 커피숍 전체 목록 조회
  List<CoffeeShop> selectAll();
  List<CoffeeShop> selectAll(int startRec, int endRec);

  // 커피숍 전체 목록 조회 (관리자)
  List<CoffeeShop> selectAll(CoffeeShopFilterCondition coffeeShopFilterCondition);

  // 커피숍 개별 조회
  CoffeeShop selectOne(Long shopId);

  // 커피숍 삭제
  int deleteCoffeeShop(Long shopId);

  // 커피숍 수정
  int updateCoffeeShop(Long shopId, CoffeeShop coffeeShop);

  // 등록된 커피숍 전체건수
  int totalCount();
  int totalCount(CoffeeShopFilterCondition coffeeShopFilterCondition);

  // 북마크(찜) 체크 여부 판단
  Boolean selectShopBookmark(Long shopId, Long memberId);

  // 조회수 증가(카운트)
  Long updateViewCnt(Long shopId);

  // 찜 증가(카운트)
  Long updateBookmarkCnt(Long shopId);

  // 리뷰 수 증가(카운트)
  Long updateShopReviewCnt(Long shopId);


}
