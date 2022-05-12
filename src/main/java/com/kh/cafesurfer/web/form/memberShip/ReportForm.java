package com.kh.cafesurfer.web.form.memberShip;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReportForm {
 private Long reviewId;         // REVIEW_ID	        NUMBER(5,0)
 private int reportTypeCode;    // REPORT_TYPE_CODE	  NUMBER(1,0)


 private String reviewContent;        // REVIEW_CONTENT	  CLOB
 private LocalDateTime reviewCdate;   // REVIEW_CDATE	    TIMESTAMP(6)
 private int reportCnt;               // REPORT_COUNT	    NUMBER(3,0)
 private Long memberId;               // MEMBER_ID	        NUMBER(5,0)
 private Long shopId;                 // SHOP_ID           NUMBER(5,0)

 private String memberName;
 private String memberEmail;     //  MEMBER_EMAIL	VARCHAR2(40 BYTE)
 private String memberPasswd;    //  MEMBER_PASSWD	VARCHAR2(20 BYTE)
 private String memberGender;    //  MEMBER_GENDER	CHAR(3 BYTE)
 private Long memberAge;         //  MEMBER_AGE	NUMBER(8,0)
 private String memberTel;       //  MEMBER_TEL	VARCHAR2(15 BYTE)

 private String shopName;              // SHOP_NAME	          NVARCHAR2(30 CHAR)
 private String shopAddress;           // SHOP_ADDRESS         VARCHAR2(105 BYTE)
 private String shopTel;               // SHOP_TEL	            VARCHAR2(15 BYTE)
 private Long viewCnt;               // VIEW_COUNT	            NUMBER(5,0)
 private Long shopBookmarkCnt;         // SHOP_BOOKMARK_COUNT	NUMBER(5,0)
 private Long shopReviewCnt;           // SHOP_REVIEW_COUNT    NUMBER(5,0)
 private String parking;                // PARKING	            NUMBER
 private String allday;                // 24HOURS	              NUMBER
 private LocalDateTime shopCdate;      // SHOP_CDATE           TIMESTAMP(6)
 private String bcategoryB0101;
 private String bcategoryB0102;
 private String bcategoryB0103;
 private String bcategoryB0104;
 private String bcategoryB0105;


}
