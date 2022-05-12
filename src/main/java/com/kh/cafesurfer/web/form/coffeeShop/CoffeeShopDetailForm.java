package com.kh.cafesurfer.web.form.coffeeShop;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data

public class CoffeeShopDetailForm {

  private Long shopId;                   // SHOP_ID	              NUMBER(5,0)
  private String shopName;               // SHOP_NAME	            NVARCHAR2(30 CHAR)
  private String shopAddress;            // SHOP_ADDRESS          VARCHAR2(105 BYTE)
  private String shopTel;                // SHOP_TEL	            VARCHAR2(15 BYTE)
  private Long viewCnt;                  // VIEW_COUNT	          NUMBER(5,0)
  private Long shopBookmarkCnt;          // SHOP_BOOKMARK_COUNT 	NUMBER(5,0)
  private Long shopReviewCnt;            // SHOP_REVIEW_COUNT     NUMBER(5,0)
  private String parking;                // PARKING
  private String allday;                 // 24HOURS
  private LocalDateTime shopCdate;       // SHOP_CDATE            TIMESTAMP(6)

  private Long memberId;                 // MEMBER_ID	NUMBER(5,0)
  private String memberEmail;
  private String memberName;

  private List<MultipartFile> files;     //첨부파일

  private String store_filename;         // STORE_FILENAME	       VARCHAR2(50 BYTE)
  private String upload_filename;        // UPLOAD_FILENAME	       VARCHAR2(50 BYTE)

  //가게별 대표 해시태그 4개
  private String hcategoryh0101;          // 음료
  private String hcategoryh0102;          // 지역
  private String hcategoryh0103;          // 분위기
  private String hcategoryh0104;          // 뷰
}
