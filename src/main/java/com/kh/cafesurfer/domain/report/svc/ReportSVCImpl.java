package com.kh.cafesurfer.domain.report.svc;

import com.kh.cafesurfer.domain.report.Report;
import com.kh.cafesurfer.domain.report.dao.ReportDAO;
import com.kh.cafesurfer.domain.review.dao.ReviewDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReportSVCImpl implements ReportSVC{

  private final ReportDAO reportDAO;
  private final ReviewDAO reviewDAO;

  // 리뷰 신고
  @Override
  public void insertReport(Long reviewId, int reportTypeCode) {
    reportDAO.insertReport(reviewId, reportTypeCode);
    reviewDAO.plusReportCnt(reviewId);
  }

  @Override
  public List<Report> selectOne(Long reviewId) {
    return reportDAO.selectOne(reviewId);
  }

  @Override
  public List<Report> selectAll() {
    return reportDAO.selectAll();
  }


}
