package com.kh.cafesurfer.domain.CoffeeShop.svc;


import com.kh.cafesurfer.domain.CoffeeShop.CoffeeShop;
import com.kh.cafesurfer.domain.CoffeeShop.dao.CoffeeShopDAO;
import com.kh.cafesurfer.domain.CoffeeShop.dao.CoffeeShopFilterCondition;
import com.kh.cafesurfer.domain.common.file.svc.UploadFileSVC;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class CoffeeShopSVCImpl implements CoffeeShopSVC {

  private final CoffeeShopDAO coffeeShopDAO;
  private final UploadFileSVC uploadFileSVC;


  private String CODE = "F0101";

  // 커피숍 등록
  @Override
  public Long addCoffeeShop(CoffeeShop coffeeShop) {
    return coffeeShopDAO.insertCoffeeShop(coffeeShop);
  }

  // 커피숍 등록-첨부파일
  @Override
  public Long addCoffeeShop(CoffeeShop coffeeShop, List<MultipartFile> files1, List<MultipartFile> files2, List<MultipartFile> files3, List<MultipartFile> files4,List<MultipartFile> files5) {

    // 1) 커피숍 저장
    Long shopId = addCoffeeShop(coffeeShop);

    // 2) 첨부 저장
    uploadFileSVC.addFile(coffeeShop.getBcategoryB0101(),shopId, files1);
    uploadFileSVC.addFile(coffeeShop.getBcategoryB0102(),shopId, files2);
    uploadFileSVC.addFile(coffeeShop.getBcategoryB0103(),shopId, files3);
    uploadFileSVC.addFile(coffeeShop.getBcategoryB0104(),shopId, files4);
    uploadFileSVC.addFile(coffeeShop.getBcategoryB0105(),shopId, files5);

    return shopId;
  }

  // 커피숍 목록
  @Override
  public List<CoffeeShop> findAll() {
    return coffeeShopDAO.selectAll();
  }

  @Override
  public List<CoffeeShop> findAll(int startRec, int endRec) {
    return coffeeShopDAO.selectAll(startRec,endRec);
  }

  // 커피숍 검색
  @Override
  public List<CoffeeShop> findAll(CoffeeShopFilterCondition coffeeShopFilterCondition) {
    return coffeeShopDAO.selectAll(coffeeShopFilterCondition);
  }

  // 커피숍 상세조회
  @Override
  public CoffeeShop findByShopId(Long shopId) {
    CoffeeShop findedItem = coffeeShopDAO.selectOne(shopId);
    return findedItem;
  }

  // 커피숍 삭제
  @Override
  public int removeCoffeeShop(Long shopId) {
    // 1) 첨부파일 삭제
    String bcategoryB0101 = coffeeShopDAO.selectOne(shopId).getBcategoryB0101();
    String bcategoryB0102 = coffeeShopDAO.selectOne(shopId).getBcategoryB0102();
    String bcategoryB0103 = coffeeShopDAO.selectOne(shopId).getBcategoryB0103();
    String bcategoryB0104 = coffeeShopDAO.selectOne(shopId).getBcategoryB0104();
    String bcategoryB0105 = coffeeShopDAO.selectOne(shopId).getBcategoryB0105();
    uploadFileSVC.deleteFileByCodeWithRid(bcategoryB0101, shopId);
    uploadFileSVC.deleteFileByCodeWithRid(bcategoryB0102, shopId);
    uploadFileSVC.deleteFileByCodeWithRid(bcategoryB0103, shopId);
    uploadFileSVC.deleteFileByCodeWithRid(bcategoryB0104, shopId);
    uploadFileSVC.deleteFileByCodeWithRid(bcategoryB0105, shopId);

    // 2) 커피숍 삭제
    int affectedRow = coffeeShopDAO.deleteCoffeeShop(shopId);

    return affectedRow;
  }

  // 커피숍 수정
  @Override
  public int modifyCoffeeShop(Long shopId, CoffeeShop coffeeShop) {
    return coffeeShopDAO.updateCoffeeShop(shopId, coffeeShop);
  }

  // 커피숍 수정-첨부파일
  @Override
  public int modifyCoffeeShop(CoffeeShop coffeeShop, Long rid, List<MultipartFile> files1, List<MultipartFile> files2, List<MultipartFile> files3, List<MultipartFile> files4,  List<MultipartFile> files5) {

    // 1) 수정
    int affectedRow = modifyCoffeeShop(rid, coffeeShop);

    // 2) 첨부 저장
    uploadFileSVC.addFile(coffeeShop.getBcategoryB0101(),rid,files1);
    uploadFileSVC.addFile(coffeeShop.getBcategoryB0102(),rid,files2);
    uploadFileSVC.addFile(coffeeShop.getBcategoryB0103(),rid,files3);
    uploadFileSVC.addFile(coffeeShop.getBcategoryB0104(),rid,files4);
    uploadFileSVC.addFile(coffeeShop.getBcategoryB0105(),rid,files5);

    return affectedRow;
  }



  // 전체 건수
  @Override
  public int totalCount() {
    return coffeeShopDAO.totalCount();
  }
  
  @Override
  public int totalCount(CoffeeShopFilterCondition coffeeShopFilterCondition) {
    return coffeeShopDAO.totalCount(coffeeShopFilterCondition);
  }

  @Override
  public Boolean selectShopBookmark(Long shopId, Long memberId) {
    return coffeeShopDAO.selectShopBookmark(shopId, memberId);
  }

  // 조회수 카운트(증가)
  @Override
  public Long increaseViewCnt(Long shopId) {
    return coffeeShopDAO.updateViewCnt(shopId);
  }

  // 찜 카운트(증가)
  @Override
  public Long increaseBookmarkCnt(Long shopId) {
    return coffeeShopDAO.updateBookmarkCnt(shopId);
  }

  // 리뷰 카운트(증가)
  @Override
  public Long increaseShopReviewCnt(Long shopId) {
    return coffeeShopDAO.updateShopReviewCnt(shopId);
  }

}
