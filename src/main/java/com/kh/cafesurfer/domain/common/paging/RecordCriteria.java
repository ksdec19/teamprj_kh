package com.kh.cafesurfer.domain.common.paging;

import lombok.ToString;

/**
 * 한 페이지에 보여줄 레코드 계산
 */
@ToString
public class RecordCriteria {
  private int reqPage = 1;                // 요청페이지
  private final int REC_COUNT_PER_PAGE;   // 한 페이지에 보여줄 레코드 수
  private int startRec;                   // 한 페이지에 보여줄 시작 레코드
  private int endRec;                     // 한 페이지에 보여줄 종료 레코드

  public RecordCriteria(int REC_COUNT_PER_PAGE) {
    this.REC_COUNT_PER_PAGE = REC_COUNT_PER_PAGE;
  }
  /**
   * 시작 레코드
   * (요청페이지-1)*한 페이지에 보여줄 레코드 수+1
   * @return 시작 레코드 번호
   */
  public int getStartRec() {
    return this.startRec;
  }
  /**
   * 종료 레코드
   * 한 페이지에 보여줄 레코드 수 * 요청페이지
   * @return 종료 레코드
   */
  public int getEndRec() {
    return this.endRec;
  }

  public int getReqPage() {
    return reqPage;
  }

  /**
   * 요청페이지를 입력받아 시작 레코드, 종료 레코드를 구한다.
   * @param reqPage
   */
  public void setReqPage(int reqPage) {
    this.reqPage = reqPage;
    this.startRec = (reqPage - 1) * REC_COUNT_PER_PAGE + 1;
    this.endRec = REC_COUNT_PER_PAGE * reqPage;
  }

  public int getREC_COUNT_PER_PAGE() {
    return REC_COUNT_PER_PAGE;
  }
}
