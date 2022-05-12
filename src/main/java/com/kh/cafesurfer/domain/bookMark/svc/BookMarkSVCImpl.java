package com.kh.cafesurfer.domain.bookMark.svc;

import com.kh.cafesurfer.domain.bookMark.Bookmark;
import com.kh.cafesurfer.domain.bookMark.dao.BookMarkDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookMarkSVCImpl implements BookMarkSVC{

  private final BookMarkDAO bookMarkDAO;
  
  //등록
  @Override
  public void insertBookMark(Long memberId, Long shopId) {
    bookMarkDAO.insertBookMark(memberId,shopId);
    bookMarkDAO.plusBookmarkCnt(shopId);

  }
  
  //삭제
  @Override
  public void deleteBookMark(Long memberId, Long shopId) {
    bookMarkDAO.deleteBookMark(memberId,shopId);
    bookMarkDAO.minusBookmarkCnt(shopId);
  }
  
  //조회
  @Override
  public List<Bookmark> selectBookMarkByMemberId(Long memberId) {
    return bookMarkDAO.selectBookMarkByMemberId(memberId);
  }
}
