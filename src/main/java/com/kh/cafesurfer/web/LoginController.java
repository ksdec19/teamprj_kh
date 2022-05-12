package com.kh.cafesurfer.web;

import com.kh.cafesurfer.domain.memberShip.MemberShip;
import com.kh.cafesurfer.domain.memberShip.svc.MemberShipSVC;
import com.kh.cafesurfer.web.form.login.LoginForm;
import com.kh.cafesurfer.web.form.login.LoginMemberShip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

  private final MemberShipSVC memberShipSVC;

  //로그인
  @GetMapping("/login")
  public String loginForm(Model model){
    model.addAttribute("loginForm", new LoginForm());
    return "login/loginForm";
  }
  //로그인처리
  @PostMapping("/login")
  public String login(
      @Valid
      @ModelAttribute
          LoginForm loginForm,
      BindingResult bindingResult,
      HttpServletRequest request,
      @RequestParam(required = false,defaultValue = "/") String redirectUrl
  ){

    //필드 유효성 체크
    if(bindingResult.hasErrors()){
      log.info("loginError={}", bindingResult);
      return "login/loginForm";
    }

    //오브젝트 체크 : 회원유무
    if(!memberShipSVC.existMemberByEmail(loginForm.getMemberEmail())) {
      bindingResult.reject("loginFail.email");

      return "login/loginForm";
    }


    //오브젝트 체크 :로그인
    MemberShip memberShip = memberShipSVC.login(loginForm.getMemberEmail(), loginForm.getMemberPasswd());
    if(memberShip == null){
      bindingResult.reject("loginFail.passwd");
      return "login/loginForm";
    }

    //회원 세션 정보
    LoginMemberShip loginMemberShip = new LoginMemberShip(memberShip.getMemberEmail(), memberShip.getMemberName(),memberShip.getMemberId(), memberShip.getMemberTel());

    //인증성공
    //세션이 있으면 세션 반환, 없으면 새롭게 생성
    HttpSession session = request.getSession(true);
    session.setAttribute(SessionConst.LOGIN_MEMBER, loginMemberShip);

    if(loginMemberShip.getMemberEmail().equals("admin@surfer.com")) {
      return "redirect:/admin/coffeeShop/list";
    }

    if (redirectUrl.equals("/login/loginForm")) {
      return "redirect:/";
    }

    return "redirect:"+redirectUrl;
  }
  //로그아웃
  @GetMapping("/logout")
  public String logout(HttpServletRequest request){

    //세션이 있으면 세션을 반환하고 없으면 null반환
    HttpSession session = request.getSession(false);
    if(session != null){
      session.invalidate();  //세션 제거
    }

    return "redirect:/";
  }
  
}
