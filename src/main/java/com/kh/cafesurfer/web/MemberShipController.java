package com.kh.cafesurfer.web;

import com.kh.cafesurfer.domain.bookMark.Bookmark;
import com.kh.cafesurfer.domain.bookMark.svc.BookMarkSVC;
import com.kh.cafesurfer.domain.memberShip.MemberShip;
import com.kh.cafesurfer.domain.memberShip.svc.MemberShipSVC;
import com.kh.cafesurfer.domain.review.Review;
import com.kh.cafesurfer.domain.review.svc.ReviewSVC;
import com.kh.cafesurfer.web.form.memberShip.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberShipController {

  private final MemberShipSVC memberShipSVC;
  private final BookMarkSVC bookMarkSVC;
  private final ReviewSVC reviewSVC;

  //성별
  @ModelAttribute("memberGender")
  public MemberGender[] gender(){

    return MemberGender.values();  //[MALE, FEMALE]
  }

  //회원가입
  @GetMapping("/join")
  public String joinForm(@ModelAttribute JoinForm joinForm){
    log.info("joinForm() 호출됨!");
    return "memberJoin/memberJoinpage";
  }

  //회원가입처리
  @PostMapping("/join")
  public String join(
      @Valid @ModelAttribute JoinForm joinForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes){

    log.info("join() 호출됨!");
    log.info("MemberEmail={}, MemberPasswd={}, MemberPasswdChk={}, MemberName={},MemberGender={},MemberAge={},MemberTel={}",
        joinForm.getMemberEmail(),joinForm.getMemberPasswd(),
        joinForm.getMemberPasswdChk(),joinForm.getMemberName(),joinForm.getMemberGender(),joinForm.getMemberAge(),joinForm.getMemberTel());

    //1)유효성체크 - 필드오류
    if(bindingResult.hasErrors()){
      log.info("error={}", bindingResult);
      return "memberJoin/memberJoinpage";
    }
    //2)아이디 중복체크
    if(memberShipSVC.existMemberByEmail(joinForm.getMemberEmail())){
      bindingResult.rejectValue("getMemberEmail","joinForm.MemberEmail.dup");
      log.info("error={}", bindingResult);
      return "memberJoin/memberJoinpage";
    }

    //전화번호 중복체크
    if(memberShipSVC.existMemberByTel(joinForm.getMemberTel())){
      bindingResult.rejectValue("getMemberTel","joinForm.MemberTel.dup");
      log.info("error={}", bindingResult);
      return "memberJoin/memberJoinpage";
    }


    //3)유효성체크 - global 오류 (2개이상의 필드체크, 백앤드로직 수행시 발생오류)
    //비밀번호 != 비빌번호체크
    if(!joinForm.getMemberPasswd().equals(joinForm.getMemberPasswdChk()))   {
      bindingResult.reject("member.MemberPasswdChk");
      return "memberJoin/memberJoinpage";
    }

    //4)정상처리로직
    log.info("joinForm={}", joinForm);
    MemberShip memberShip = new MemberShip( null,
        joinForm.getMemberEmail(), joinForm.getMemberPasswd(), joinForm.getMemberName(),
        joinForm.getMemberGender().getDescription(),joinForm.getMemberAge(),joinForm.getMemberTel());

    MemberShip joinedMember = memberShipSVC.addMember(memberShip);
    log.info("MemberEmail={}, MemberPasswd={}, MemberName={}",
        joinedMember.getMemberEmail(),joinedMember.getMemberPasswd(),joinedMember.getMemberName());

    return "redirect:/login";
  }

  @GetMapping("/joinSuccess")
  public String joinSuccess(){
    return "memberShip/joinSuccess";
  }

  //회원수정
  @GetMapping("/{MemberEmail}/memberModify")
  public String modifyForm(
      @PathVariable("MemberEmail") String MemberEmail,
      Model model){

    MemberShip memberShip = memberShipSVC.memberFindByEmail(MemberEmail);

    ModifyForm modifyForm = new ModifyForm();
    modifyForm.setMemberEmail(memberShip.getMemberEmail());
    modifyForm.setMemberName(memberShip.getMemberName());
    modifyForm.setMemberGender(getGender(memberShip.getMemberGender()));
    modifyForm.setMemberAge(memberShip.getMemberAge());
    modifyForm.setMemberTel(memberShip.getMemberTel());

    model.addAttribute("modifyForm", modifyForm);

    return "memberJoin/memberModifypage";
  }

  //문자열로 Enum객체에서 상수요소 찾아오기
  private MemberGender getGender(String gender) {
    MemberGender finded = null;
    for(MemberGender g : MemberGender.values()){
      if(g.getDescription().equals(gender)){
        finded = MemberGender.valueOf(g.name());
        break;
      }
    }
    return finded;
  }
  //회원수정 처리
  @PostMapping("/{MemberEmail}/memberModify")
  public String modify(
      @Valid @ModelAttribute ModifyForm modifyForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes){

    //1) 유효성 체크  - 필드오류
    if(bindingResult.hasErrors()){
      log.info("bindingResult={}", bindingResult);
      return "memberJoin/memberModifypage";
    }

    //2) 비밀번호가 일치하는지 체크
    if(!memberShipSVC.isMember(modifyForm.getMemberEmail(), modifyForm.getMemberPasswd())){
      bindingResult.rejectValue("MemberPasswd","memberShip.MemberPasswdChk");
      log.info("bindingResult={}", bindingResult);
      return "memberJoin/memberModifypage";
    }

    //3) 회원정보 수정
    MemberShip memberShip = new MemberShip(modifyForm.getMemberEmail(), modifyForm.getMemberPasswd(), modifyForm.getMemberName(),
        modifyForm.getMemberGender().getDescription(), modifyForm.getMemberAge(), modifyForm.getMemberTel());

    memberShipSVC.modifyMember(memberShip);
    redirectAttributes.addAttribute("MemberEmail", memberShip.getMemberEmail());

    return "redirect:/members/{MemberEmail}/memberModify";  //회원 상세화면 이동
  }

  //회원탈퇴
  @GetMapping("/{memberEmail}/memberDel")
  public String outForm(@ModelAttribute OutForm outForm ){
    log.info("outForm 호출됨!");
    return "memberJoin/memberDelpage";
  }

  @PostMapping("/{memberEmail}/memberDel")
  public String out(
      @Valid @ModelAttribute OutForm outForm,
      BindingResult bindingResult,
      HttpSession session){

    log.info("out 호출됨");
    //1)유효성체크
    if(bindingResult.hasErrors()){
      log.info("bindingResult={}",bindingResult);
      return "/memberJoin/memberDelpage";
    }
    //2)동의 체크여부
    if(!outForm.getAgree()){
      bindingResult.rejectValue("agree",null, "탈퇴 안내를 확인하고 동의해 주세요.");
      return "/memberJoin/memberDelpage";
    }

    //3) 비밀번호가 일치하는지 체크
    if(!memberShipSVC.isMember(outForm.getMemberEmail(), outForm.getMemberPasswd())){
      bindingResult.rejectValue("MemberPasswd","memberShip.MemberPasswdChk");
      log.info("bindingResult={}", bindingResult);
      return "/memberJoin/memberDelpage";
    }

    //4) 탈퇴로직 수행
    //4-1) 회원정보 삭제
    memberShipSVC.removeMember(outForm.getMemberEmail());
    //4-2) 센션정보 제거
    if(session != null){
      session.invalidate();
    }
    return "redirect:/";
  }

  //회원 찜목록 페이지
  @GetMapping("/myinfo/{memberId}")
  public String myinfo(@PathVariable Long memberId,
                       Model model){
    log.info("myinfo() 호출됨");

    List<Bookmark> bookmarks = null;

    bookmarks = bookMarkSVC.selectBookMarkByMemberId(memberId);

    List<BookMarkForm> BookmarkList = new ArrayList<>();
    for (Bookmark bookmark : bookmarks) {
      BookMarkForm bookMarkForm = new BookMarkForm();
      BeanUtils.copyProperties(bookmark, bookMarkForm);
      BookmarkList.add(bookMarkForm);
    }
    model.addAttribute("BookmarkList", BookmarkList);
    log.info("BookmarkList={}", BookmarkList);

    return "mypage";
  }
  //회원 리뷰 페이지
  @GetMapping("/review/{memberId}")
  public String review(@PathVariable Long memberId,
                       Model model){
    log.info("review() 호출됨");
    List<Review> reviews = null;
    reviews = reviewSVC.findByMemberId(memberId);

    List<ReviewForm> reviewList = new ArrayList<>();
    for (Review review : reviews) {
      ReviewForm reviewForm = new ReviewForm();
      BeanUtils.copyProperties(review, reviewForm);
      reviewList.add(reviewForm);
    }
    model.addAttribute("reviewList", reviewList);
    log.info("reviewList={}", reviewList);


    return "review";
  }

  //아이디 찾기
  @GetMapping("/findEmail")
  public String findEmail(@ModelAttribute FindEmailAndPasswdForm findFrom) {

    return "memberJoin/findEmail";
  }

  //아이디 찾기 처리
  @PostMapping("/findEmail")
  public String foundEmail(@ModelAttribute FindEmailAndPasswdForm findFrom,
                           BindingResult bindingResult) {


    //필드 유효성 체크
    if(bindingResult.hasErrors()){
      log.info("loginError={}", bindingResult);
      return "memberJoin/findEmail";
    }

//    //오브젝트 체크 : 회원유무
//    if(!memberShipSVC.existMemberByEmail(findFrom.getMemberEmail())) {
//      bindingResult.reject("loginFail.email");
//
//      return "memberJoin/findEmail";
//    }




//    MemberShip memberShip = new MemberShip();
//    BeanUtils.copyProperties(findFrom, memberShip);

    memberShipSVC.findEmailByTel(findFrom.getMemberName(), findFrom.getMemberTel());
    return "memberJoin/findEmail";
  }

  //비밀번호 찾기
  @GetMapping("/findPasswd")
  public String findPasswd(@ModelAttribute FindEmailAndPasswdForm findFrom) {

    return "memberJoin/findPasswd";
  }

  //비밀번호 찾기 처리
  @PostMapping("/findPasswd")
  public String foundPasswd(@ModelAttribute FindEmailAndPasswdForm findFrom) {
    MemberShip memberShip = new MemberShip();
    BeanUtils.copyProperties(findFrom, memberShip);

    memberShipSVC.findPwByEmail(findFrom.getMemberName(), findFrom.getMemberTel(), findFrom.getMemberEmail());

    return "memberJoin/findPasswd";
  }
}
