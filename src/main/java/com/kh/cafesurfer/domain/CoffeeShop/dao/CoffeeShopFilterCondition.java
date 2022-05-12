package com.kh.cafesurfer.domain.CoffeeShop.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class CoffeeShopFilterCondition {
  private int startRec;         // 시작 레코드
  private int endRec;           // 종료 레코드
  private String searchType;    // 검색 유형
  private String keyword;       // 검색어

  public CoffeeShopFilterCondition(String searchType, String keyword) {
    this.searchType = searchType;
    this.keyword = keyword;
  }
}
