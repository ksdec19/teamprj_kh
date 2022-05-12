package com.kh.cafesurfer.domain.bookMark.svc;

import com.kh.cafesurfer.domain.bookMark.Bookmark;

import java.util.List;

public interface BookMarkSVC {

  // 찜 등록
  void insertBookMark(Long memberId, Long shopId);
  // 찜 삭제
  void deleteBookMark(Long memberId, Long shopId);
  // 찜 조회 by 회원Id
  List<Bookmark> selectBookMarkByMemberId(Long memberId);

}
