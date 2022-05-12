package com.kh.cafesurfer.web.form.review;

import lombok.Data;

@Data
public class AddForm {
  
  private Long reviewId;               // REVIEW_ID	        NUMBER(5,0)
  private String reviewContent;        // REVIEW_CONTENT	  CLOB
  private Long memberId;               // MEMBER_ID	        NUMBER(5,0)
  private Long shopId;                 // SHOP_ID           NUMBER(5,0)
}
