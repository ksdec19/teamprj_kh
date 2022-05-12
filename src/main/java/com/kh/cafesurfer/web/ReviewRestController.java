package com.kh.cafesurfer.web;

import com.kh.cafesurfer.domain.review.Review;
import com.kh.cafesurfer.domain.review.svc.ReviewSVC;
import com.kh.cafesurfer.web.api.ApiResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewRestController {

  private final ReviewSVC reviewSVC;

  //등록
  @PostMapping
  public ApiResult<Object> addReview(
      @RequestBody Review review){
    Review addedReview = reviewSVC.addReview(review, review.getShopId());
    return new ApiResult<>("00","success",addedReview);
  }

  //리뷰 단건 조회
  @GetMapping("/{reviewId}")
  public ApiResult<Object> selectReview(@PathVariable Long reviewId){
    return new ApiResult<>("00","success", reviewSVC.findByReviewId(reviewId));
  }

  //전체조회
  @GetMapping("/all")
  public ApiResult<Object> selectAll(){
    List<Review> list = reviewSVC.findAll();
    return new ApiResult<>("00","success",list);
  }

  //멤버별 조회
  @GetMapping("/member")
  public ApiResult<Object> selectAllByMember(@RequestParam Long memberId) {
    List<Review> reviewListByMember = reviewSVC.findByMemberId(memberId);
    return new ApiResult<>("00","success",reviewListByMember);
  }

  //커피숍별 조회
  @GetMapping("/shop")
  public ApiResult<Object> selectAllByCoffeeShop(@RequestParam Long shopId) {
    List<Review> reviewListByShop = reviewSVC.findByShopId(shopId);
    return new ApiResult<>("00","success",reviewListByShop);
  }

  //삭제
  @DeleteMapping("/{reviewId}")
  public ApiResult<Object> deleteReview(
      @RequestBody Review review){
    return new ApiResult<>("00","success", reviewSVC.removeReview(review.getReviewId(), review.getShopId()));
  }

}
