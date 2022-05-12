package com.kh.cafesurfer.domain.review.svc;


import com.kh.cafesurfer.domain.review.Review;
import com.kh.cafesurfer.domain.review.dao.ReviewDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewSVCImpl implements ReviewSVC {
  
  private final ReviewDAO reviewDAO;
  
  // 리뷰 등록
  @Override
  public Review addReview(Review review, Long shopId) {
    Review insertReview = reviewDAO.insertReview(review);
    reviewDAO.plusReviewCnt(shopId);
    
    return insertReview;
  }
  
  // 리뷰 전체조회
  @Override
  public List<Review> findAll() {
    return reviewDAO.selectAll();
  }
  
  // 리뷰 건별조회(리뷰Id)
  @Override
  public Review findByReviewId(Long reviewId) {
    return reviewDAO.selectOne(reviewId);
  }
  
  // 회원Id별 리뷰조회
  @Override
  public List<Review> findByMemberId(Long memberId) {
    return reviewDAO.selectByMemberId(memberId);
  }
  
  // 커피숍별 리뷰조회
  @Override
  public List<Review> findByShopId(Long shopId) {
    return reviewDAO.selectByShopId(shopId);
  }
  
  // 리뷰삭제
  @Override
  public Review removeReview(Long reviewId, Long shopId) {
  
    Review deleteReview = reviewDAO.deleteReview(reviewId);
    reviewDAO.minusReviewCnt(shopId);
    
    return deleteReview;
  }
  
  // 신고수 증가
  @Override
  public int increaseReportCnt(Long reviewId) {
    return reviewDAO.plusReportCnt(reviewId);
  }

  // 신고별 목록
  @Override
  public List<Review> selectByReport() {
    return reviewDAO.selectByReport();
  }
}
