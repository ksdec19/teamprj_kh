package com.kh.cafesurfer.domain.review.svc;

import com.kh.cafesurfer.domain.review.Review;

import java.util.List;

public interface ReviewSVC {
  
  // 등록
  Review addReview(Review review, Long shopId);
  
  // 조회(전체)
  List<Review> findAll();
  
  // 조회(리뷰Id로)
  Review findByReviewId(Long reviewId);
  
  // 조회(회원Id로)
  List<Review> findByMemberId(Long memberId);
  
  // 조회(커피숍Id로)
  List<Review> findByShopId(Long shopId);
  
  // 삭제
  Review removeReview(Long reviewId, Long shopId);
  
  // 신고수 증가(카운트)
  int increaseReportCnt(Long reviewId);

  // 신고별 목록
  List<Review> selectByReport();
  
}
