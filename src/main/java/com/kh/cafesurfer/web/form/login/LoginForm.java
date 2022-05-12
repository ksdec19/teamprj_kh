package com.kh.cafesurfer.web.form.login;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class LoginForm {

  @NotBlank
  @Email
  @Size(min = 4, max=50)
  private String memberEmail;

  @NotBlank
  @Size(min = 4, max=8)
  private String memberPasswd;
}
