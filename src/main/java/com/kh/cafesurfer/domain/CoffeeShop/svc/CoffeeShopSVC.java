package com.kh.cafesurfer.domain.CoffeeShop.svc;


import com.kh.cafesurfer.domain.CoffeeShop.CoffeeShop;
import com.kh.cafesurfer.domain.CoffeeShop.dao.CoffeeShopFilterCondition;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CoffeeShopSVC {

    // 커피숍 등록 - 첨부파일 없음
    Long addCoffeeShop(CoffeeShop coffeeShop);
    // 커피숍 등록 - 첨부파일 있음
    Long addCoffeeShop(CoffeeShop coffeeShop, List<MultipartFile> files1, List<MultipartFile> files2, List<MultipartFile> files3, List<MultipartFile> files4, List<MultipartFile> files5);

    // 전체목록
    List<CoffeeShop> findAll();
    List<CoffeeShop> findAll(int startRec, int endRec);

    // 전체목록 검색
    List<CoffeeShop> findAll(CoffeeShopFilterCondition coffeeShopFilterCondition);

    // 커피숍 개별 조회
    CoffeeShop findByShopId(Long shopId);

    // 커피숍 삭제
    int removeCoffeeShop(Long shopId);

    // 커피숍 수정 - 첨부파일 없음
    int modifyCoffeeShop(Long shopId, CoffeeShop coffeeShop);
    // 커피숍 수정 - 첨부파일 있음
    int modifyCoffeeShop(CoffeeShop coffeeShop, Long rid, List<MultipartFile> files1, List<MultipartFile> files2, List<MultipartFile> files3, List<MultipartFile> files4,List<MultipartFile> files5);

    // 전체건수
    int totalCount();
    int totalCount(CoffeeShopFilterCondition coffeeShopFilterCondition);

    // 찜 체크여부 판단
    Boolean selectShopBookmark(Long shopId, Long memberId);

    // 조회수 증가(카운트)
    Long increaseViewCnt(Long shopId);

    // 북마크 수(카운트)
    Long increaseBookmarkCnt(Long shopId);

    // 리뷰 수(카운트)
    Long increaseShopReviewCnt(Long shopId);
  }
