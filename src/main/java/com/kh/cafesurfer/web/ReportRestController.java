package com.kh.cafesurfer.web;

import com.kh.cafesurfer.domain.report.Report;
import com.kh.cafesurfer.domain.report.svc.ReportSVC;
import com.kh.cafesurfer.web.api.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
public class ReportRestController {

  private final ReportSVC reportSVC;

  //등록
  @PostMapping
  public ApiResult<Object> addReport(
      @RequestBody Report report){
    reportSVC.insertReport(report.getReviewId(), report.getReportTypeCode());
    return new ApiResult<>("00","success", report.getReviewId());
  }

  //신고된 리뷰 단건 조회
  @GetMapping("/{reviewId}")
  public ApiResult<Object> selectReview(@PathVariable Long reviewId){
    return new ApiResult<>("00","success", reportSVC.selectOne(reviewId));
  }

  //신고된 리뷰 전체조회
  @GetMapping("/all")
  public ApiResult<Object> selectAll(){
    List<Report> list = reportSVC.selectAll();
    return new ApiResult<>("00","success", list);
  }
}
