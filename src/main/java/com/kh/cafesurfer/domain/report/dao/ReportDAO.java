package com.kh.cafesurfer.domain.report.dao;

import com.kh.cafesurfer.domain.report.Report;

import java.util.List;

public interface ReportDAO {
  // 신고
  void insertReport(Long reviewId, int reportTypeCode);
  
  // 신고조회(건별)
  List<Report> selectOne(Long reviewId);
  
  // 전체목록
  List<Report> selectAll();


}
