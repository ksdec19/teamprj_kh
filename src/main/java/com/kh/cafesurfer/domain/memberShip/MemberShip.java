package com.kh.cafesurfer.domain.memberShip;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberShip {

  private Long memberId;          //  MEMBER_ID	      NUMBER(5,0)
  private String memberEmail;     //  MEMBER_EMAIL	  VARCHAR2(40 BYTE)
  private String memberPasswd;    //  MEMBER_PASSWD 	VARCHAR2(20 BYTE)
  private String memberName;      //  MEMBER_NAME	    VARCHAR2(15 BYTE)
  private String memberGender;    //  MEMBER_GENDER	  CHAR(3 BYTE)
  private Long memberAge;         //  MEMBER_AGE    	NUMBER(8,0)
  private String memberTel;       //  MEMBER_TEL    	VARCHAR2(15 BYTE)


  public MemberShip(String memberEmail, String memberPasswd, String memberName, String memberGender, Long memberAge, String memberTel) {
    this.memberEmail = memberEmail;
    this.memberPasswd = memberPasswd;
    this.memberName = memberName;
    this.memberGender = memberGender;
    this.memberAge = memberAge;
    this.memberTel = memberTel;

  }
}
