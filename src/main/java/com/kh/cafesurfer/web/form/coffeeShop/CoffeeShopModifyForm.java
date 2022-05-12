package com.kh.cafesurfer.web.form.coffeeShop;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CoffeeShopModifyForm {

  @NotBlank @Size(min = 1, max = 30)
  private String shopName;              // SHOP_NAME           VARCHAR2(30 CHAR)
  @NotBlank @Size(min = 1, max = 105)
  private String shopAddress;           // SHOP_ADDRESS        VARCHAR2(105 BYTE)
  @NotBlank @Size(min = 8, max = 15)
  private String shopTel;               // SHOP_TEL            VARCHAR2(15 BYTE)

  @NotBlank
  private String parking;                // PARKING	           NUMBER
  @NotBlank
  private String allday;                 // 24HOURS	           NUMBER

  private Long memberId;                 // MEMBER_ID         NUMBER(5,0)
  private Long shopId;                   // SHOP_ID         	NUMBER(5,0)
  private String memberEmail;
  private String memberName;

  private List<MultipartFile> files;      // 첨부파일

  private String bcategoryB0101;
  private String bcategoryB0102;
  private String bcategoryB0103;
  private String bcategoryB0104;
  private String bcategoryB0105;

  private List<MultipartFile> files1;      // 첨부파일
  private List<MultipartFile> files2;      // 첨부파일
  private List<MultipartFile> files3;      // 첨부파일
  private List<MultipartFile> files4;      // 첨부파일
  private List<MultipartFile> files5;      // 첨부파일

  private Long uploadfileId;               // UPLOADFILE_ID	NUMBER(10,0)
  private String code;                     // CODE	VARCHAR2(11 BYTE)
  private Long rid;                        // RID	NUMBER(10,0)
  private String store_filename;           // STORE_FILENAME	VARCHAR2(50 BYTE)
  private String upload_filename;          // UPLOAD_FILENAME	VARCHAR2(50 BYTE)
  private String fsize;                    // FSIZE	VARCHAR2(45 BYTE)
  private String ftype;                    // FTYPE	VARCHAR2(50 BYTE)
  private LocalDateTime cdate;             // CDATE	TIMESTAMP(6)
  private LocalDateTime udate;             // UDATE	TIMESTAMP(6)

  //가게별 대표 해시태그 4개
  private String hcategoryh0101;            // 음료
  private String hcategoryh0102;            // 지역
  private String hcategoryh0103;            // 분위기
  private String hcategoryh0104;            // 뷰
}
