package com.kh.cafesurfer.domain.bookMark.dao;

import com.kh.cafesurfer.domain.bookMark.Bookmark;

import java.util.List;

public interface BookMarkDAO {

  // 찜 등록
  void insertBookMark(Long memberId, Long shopId);

  // 찜 삭제
  void deleteBookMark(Long memberId, Long shopId);

  // 찜 조회 by 회원Id
  List<Bookmark> selectBookMarkByMemberId(Long memberId);

  // 커피숍 찜 카운트 증가
  Long plusBookmarkCnt(Long shopId);

  // 커피숍 찜 카운트 감소
  Long minusBookmarkCnt(Long shopId);

}
