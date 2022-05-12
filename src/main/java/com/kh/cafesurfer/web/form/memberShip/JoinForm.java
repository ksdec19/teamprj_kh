package com.kh.cafesurfer.web.form.memberShip;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class JoinForm {
  @Email
  @NotBlank
  @Size(min = 4, max = 40)
  private String memberEmail;       // MEMBER_EMAIL   VARCHAR2(40 BYTE)

  @NotBlank @Size(min = 4, max = 20)
  private String memberPasswd;      // MEMBER_PASSWD   VARCHAR2(20 BYTE)

  @NotBlank @Size(min = 4, max = 20)
  private String memberPasswdChk;   // MEMBER_PASSWD   VARCHAR2(20 BYTE)

  @NotBlank  @Size(min = 2, max = 15)
  private String memberName;        // MEMBER_NAME    VARCHAR2(15 BYTE)

  @NotNull
  private MemberGender memberGender;    // 남 여

  @NotNull
  private Long memberAge;           // MEMBER_AGE      NUMBER(8,0)

  @NotBlank @Size(min = 10, max = 15)
  private String memberTel;         // MEMBER_TEL      VARCHAR2(15 BYTE)

}
