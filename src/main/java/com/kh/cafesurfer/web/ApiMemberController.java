package com.kh.cafesurfer.web;


import com.kh.cafesurfer.domain.memberShip.MemberShip;
import com.kh.cafesurfer.domain.memberShip.svc.MemberShipSVC;
import com.kh.cafesurfer.web.api.ApiResult;
import com.kh.cafesurfer.web.form.memberShip.FindEmailAndPasswdForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ApiMemberController {

  private final MemberShipSVC memberSVC;

  @ResponseBody //http응답 메세지 바디에 직접 쓰기
  //(반환타입이 객체이면 java객체=>json포맷 문자열로 변환 후)
  @GetMapping("/api/members")
  public ApiResult<List<MemberShip>> members() {
    List<MemberShip> list = memberSVC.FindAll();
    ApiResult<List<MemberShip>> result = new ApiResult<>("00", "success", list);
    return result;
  }

  @ResponseBody
  @GetMapping("/api/member")
  public ApiResult<MemberShip> member(@RequestParam String email) {
    MemberShip memberShip = memberSVC.memberFindByEmail(email);
    ApiResult<MemberShip> result = new ApiResult<>("00", "success", memberShip);
    return result;
  }

  //아이디 중복체크
  @ResponseBody
  @GetMapping("/api/members/{memberEmail}/existEmail")
  public ApiResult<MemberShip> existMemberByEmail(@PathVariable String memberEmail) {

    boolean existMemberByEmail = memberSVC.existMemberByEmail(memberEmail);

    if (existMemberByEmail) {
      return new ApiResult("00", "success", "OK");
    } else {
      return new ApiResult("99", "fail", "NOK");
    }
  }
  //전화번호 중복체크
  @ResponseBody
  @GetMapping("/api/members/{memberTel}/existTel")
  public ApiResult<MemberShip> existMemberByTel(@PathVariable String memberTel) {

    boolean existMemberByTel = memberSVC.existMemberByTel(memberTel);

    if (existMemberByTel) {
      return new ApiResult("00", "success", "OK");
    } else {
      return new ApiResult("99", "fail", "NOK");
    }
  }


  //아이디 찾기
  @ResponseBody
  @PutMapping("/api/members/memberEmail/find")
  public ApiResult<String> findEmailByEmail(@RequestBody FindEmailAndPasswdForm found) {

    log.info("memberName={}",found.getMemberName());
    log.info("memberTel={}",found.getMemberTel());
    ApiResult<String> result = null;

    String memberEmail = memberSVC.findEmailByTel(found.getMemberName(), found.getMemberTel());

    //StringUtils.isEmpty() : null 또는 ""문자열인지 체크
    //if(email == null || email.equals(""))
    if (!StringUtils.isEmpty(memberEmail)) {
      result = new ApiResult<>("00", "success", memberEmail);
    } else {
      result = new ApiResult<>("99", "fail", "찾고자 하는 아이디가 없습니다.");
    }
    return result;
  }

  //비밀번호 찾기
  @ResponseBody
  @PutMapping("/api/members/pw/find")
  public ApiResult<String> findEmailByPw(@RequestBody FindEmailAndPasswdForm found) {

    log.info("memberName={}",found.getMemberName());
    log.info("memberTel={}",found.getMemberTel());
    log.info("memberEmail={}",found.getMemberEmail());
    ApiResult<String> result = null;

    String memberPasswd = memberSVC.findPwByEmail(found.getMemberName(), found.getMemberTel(), found.getMemberEmail());

    //StringUtils.isEmpty() : null 또는 ""문자열인지 체크
    //if(email == null || email.equals(""))
    if (!StringUtils.isEmpty(memberPasswd)) {
      result = new ApiResult<>("00", "success", memberPasswd);
    } else {
      result = new ApiResult<>("99", "fail", "일치하는 정보가 없습니다.");
    }
    return result;
  }


}