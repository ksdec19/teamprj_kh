package com.kh.cafesurfer.web;

import com.kh.cafesurfer.domain.bookMark.Bookmark;
import com.kh.cafesurfer.domain.bookMark.svc.BookMarkSVC;
import com.kh.cafesurfer.web.api.ApiResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkRestController {

  private final BookMarkSVC bookMarkSVC;

  //등록
  @PostMapping
  public void addBookmark(@RequestBody Bookmark bookMark){

    bookMarkSVC.insertBookMark(bookMark.getMemberId(), bookMark.getShopId());
  }

  //삭제
  @DeleteMapping()
  public void deleteBookmark(@RequestBody Bookmark bookMark){

    bookMarkSVC.deleteBookMark(bookMark.getMemberId(), bookMark.getShopId());
  }


  //조회
  @GetMapping("/{memberId}")
  public ApiResult<Object> selectBookmark(@PathVariable Long memberId){

     return new ApiResult<>("00","success", bookMarkSVC.selectBookMarkByMemberId(memberId));

  }

}
