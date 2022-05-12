package com.kh.cafesurfer.web.form.memberShip;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class OutForm {
  @NotBlank
  @Email
  @Size(min = 4, max = 50)
  private String memberEmail;     //  MEMBER_EMAIL	VARCHAR2(40 BYTE)

  @NotBlank
  @Size(min = 4, max = 12)
  private String memberPasswd;    //  MEMBER_PASSWD	VARCHAR2(20 BYTE)

  private Boolean agree;
}
