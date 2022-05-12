package com.kh.cafesurfer.web.form.memberShip;

import lombok.Data;

@Data
public class DetailForm {

  private String memberEmail;           //  MEMBER_EMAIL	  VARCHAR2(40 BYTE)
  private String memberPasswd;          //  MEMBER_PASSWD	  VARCHAR2(20 BYTE)
  private String memberName;            //  MEMBER_NAME	    VARCHAR2(15 BYTE)
  private MemberGender memberGender;    //  남 여
  private Long memberAge;               //  MEMBER_AGE	    NUMBER(3,0)
  private String memberTel;             //  MEMBER_TEL	    VARCHAR2(15 BYTE)
}
