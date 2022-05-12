package com.kh.cafesurfer.web.form.coffeeShop;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class CoffeeShopJoinForm {

  @NotBlank @Size(min = 1, max = 30)
  private String shopName;                // SHOP_NAME             NVARCHAR2(30 CHAR)
  @NotBlank @Size(min = 1, max = 105)
  private String shopAddress;             // SHOP_ADDRESS          VARCHAR2(105 BYTE)
  @NotBlank @Size(min = 8, max = 15)
  private String shopTel;                 // SHOP_TEL              VARCHAR2(15 BYTE)


  private String parking;                 // PARKING
  private String allday;                  // 24HOURS

  private Long memberId;                  // MEMBER_ID	            NUMBER(5,0)
  private Long shopId;                    // SHOP_ID	              NUMBER(5,0)
  private String memberEmail;
  private String memberName;

  private String bcategoryB0101;
  private String bcategoryB0102;
  private String bcategoryB0103;
  private String bcategoryB0104;
  private String bcategoryB0105;

  private List<MultipartFile> files1;       // 첨부파일
  private List<MultipartFile> files2;       // 첨부파일
  private List<MultipartFile> files3;       // 첨부파일
  private List<MultipartFile> files4;       // 첨부파일
  private List<MultipartFile> files5;       // 첨부파일

  //가게별 대표 해시태그 4개
  private String hcategoryh0101;            // 음료
  private String hcategoryh0102;            // 지역
  private String hcategoryh0103;            // 분위기
  private String hcategoryh0104;            // 뷰

}
