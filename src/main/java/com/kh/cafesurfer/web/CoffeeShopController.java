package com.kh.cafesurfer.web;


import com.kh.cafesurfer.domain.CoffeeShop.CoffeeShop;
import com.kh.cafesurfer.domain.CoffeeShop.dao.CoffeeShopFilterCondition;
import com.kh.cafesurfer.domain.CoffeeShop.svc.CoffeeShopSVC;
import com.kh.cafesurfer.domain.common.code.CodeDAO;
import com.kh.cafesurfer.domain.common.file.UploadFile;
import com.kh.cafesurfer.domain.common.file.svc.UploadFileSVC;
import com.kh.cafesurfer.domain.common.paging.FindCriteria;
import com.kh.cafesurfer.domain.memberShip.MemberShip;
import com.kh.cafesurfer.domain.memberShip.svc.MemberShipSVC;
import com.kh.cafesurfer.domain.review.Review;
import com.kh.cafesurfer.domain.review.svc.ReviewSVC;
import com.kh.cafesurfer.web.form.coffeeShop.*;
import com.kh.cafesurfer.web.form.login.LoginMemberShip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/coffeeShop")
@RequiredArgsConstructor
public class CoffeeShopController {
  private final CoffeeShopSVC coffeeShopSVC;
  private final CodeDAO codeDAO;
  private final UploadFileSVC uploadFileSVC;
  private final MemberShipSVC memberShipSVC;
  private final ReviewSVC reviewSVC;


  @ModelAttribute("parking")
  public parking[] parking(){

    return parking.values();
  }

  @ModelAttribute("allday")
  public allday[] allday(){

    return allday.values();
  }

  @Autowired
  @Qualifier("fc5") //동일한 타입의 객체가 여러개있을때 빈이름을 명시적으로 지정해서 주입받을때
  private FindCriteria fc;

  //게시판 코드,디코드 가져오기
  @ModelAttribute("classifier")
  public List<Code> classifier() {
    return codeDAO.code("B01");
  }

  @GetMapping("/add")
  public String addForm(Model model,
                        @RequestParam(required = false,defaultValue = "/") String redirectUrl,
                        HttpSession session) {

    LoginMemberShip loginMember = (LoginMemberShip) session.getAttribute(SessionConst.LOGIN_MEMBER);


    CoffeeShopJoinForm coffeeShopJoinForm = new CoffeeShopJoinForm();
    coffeeShopJoinForm.setMemberEmail(loginMember.getMemberEmail());
    coffeeShopJoinForm.setMemberName(loginMember.getMemberName());
    model.addAttribute("coffeeShopJoinForm", coffeeShopJoinForm);


    if(loginMember.getMemberEmail().equals("admin@surfer.com")) {
      return "shopAdd";
    }
    return "redirect:"+redirectUrl;
  }

  //작성처리
  @PostMapping("/add")
  public String add(
      @Valid
      @ModelAttribute CoffeeShopJoinForm coffeeShopJoinForm,  // @Valid : 유효성체크
      BindingResult bindingResult,  //폼객체에 바인딩될때 오류내용이 저장되는 객체
      HttpSession session,
      RedirectAttributes redirectAttributes) throws IOException {
    log.info("coffeeShopJoinForm={}", coffeeShopJoinForm);

//     유효성체크 로직
    if (bindingResult.hasErrors()) {
      log.info("add/bindingResult={}", bindingResult);
      return "shopAdd";
    }

    CoffeeShop coffeeShop = new CoffeeShop();
    BeanUtils.copyProperties(coffeeShopJoinForm, coffeeShop);
    //세션 가져오기
    LoginMemberShip loginMember = (LoginMemberShip) session.getAttribute(SessionConst.LOGIN_MEMBER);
    //세션 정보가 없으면 로그인페이지로 이동
    if (loginMember == null) {
      return "redirect:/login";
    }
    //세션에서 이메일, 별칭가져오기
    coffeeShop.setMemberEmail(loginMember.getMemberEmail());
    coffeeShop.setMemberName(loginMember.getMemberName());

    Long originId = 0l;

    //파일첨부유무
    if (coffeeShopJoinForm.getFiles5().size() >= 1) {
      originId = coffeeShopSVC.addCoffeeShop(coffeeShop, coffeeShopJoinForm.getFiles1(),coffeeShopJoinForm.getFiles2(),coffeeShopJoinForm.getFiles3(),coffeeShopJoinForm.getFiles4(),coffeeShopJoinForm.getFiles5());
    }

    return "redirect:/coffeeShop/list";
  }

  @GetMapping({"/list",
      "/list/{reqPage}",
      "/list/{reqPage}//",
      "/list/{reqPage}/{searchType}/{keyword}"})
  public String listAndReqPage(
      @PathVariable(required = false) Optional<Integer> reqPage,
      @PathVariable(required = false) Optional<String> searchType,
      @PathVariable(required = false) Optional<String> keyword,
      HttpServletRequest request,
      Model model) {
    log.info("/list 요청됨{},{},{}",reqPage,searchType,keyword);

    //FindCriteria 값 설정
    fc.getRc().setReqPage(reqPage.orElse(1)); //요청페이지, 요청없으면 1
    fc.setSearchType(searchType.orElse(""));  //검색유형
    fc.setKeyword(keyword.orElse(""));        //검색어

    List<CoffeeShop> list = null;

      //검색어 있음
      if(searchType.isPresent() && keyword.isPresent()){
        CoffeeShopFilterCondition coffeeShopFilterCondition = new CoffeeShopFilterCondition(
            fc.getRc().getStartRec(), fc.getRc().getEndRec(),
            searchType.get(),
            keyword.get());
        fc.setTotalRec(coffeeShopSVC.totalCount(coffeeShopFilterCondition));
        fc.setSearchType(searchType.get());
        fc.setKeyword(keyword.get());
        list = coffeeShopSVC.findAll(coffeeShopFilterCondition);

        //검색어 없음
      }else {
        //총레코드수
        fc.setTotalRec(coffeeShopSVC.totalCount());
        list = coffeeShopSVC.findAll(fc.getRc().getStartRec(), fc.getRc().getEndRec());
      }

    List<CoffeeShopListForm> partOfList = new ArrayList<>();

    for (CoffeeShop coffeeShop : list) {
      CoffeeShopListForm coffeeShopListForm = new CoffeeShopListForm();
      BeanUtils.copyProperties(coffeeShop, coffeeShopListForm);
      partOfList.add(coffeeShopListForm);
    }

    model.addAttribute("list", partOfList);
    model.addAttribute("fc",fc);

    log.info("list={}",list);


    HttpSession session = request.getSession(false);
    if (session != null) {
        return "mainAfterLogin";
      }
      return "mainBeforeLogin";
  }


  //조회
  @GetMapping("/detail/{shopId}")
  public String detail(@PathVariable Long shopId,
                       HttpServletRequest request,
                       Model model) {

    CoffeeShop coffeeShop = coffeeShopSVC.findByShopId(shopId);
    CoffeeShopDetailForm coffeeShopDetailForm = new CoffeeShopDetailForm();

    BeanUtils.copyProperties(coffeeShop, coffeeShopDetailForm);

    HttpSession session = request.getSession(false);

    //북마크 등록 여부 판단
    if (session != null) {
      LoginMemberShip loginMemberShip = (LoginMemberShip)session.getAttribute(SessionConst.LOGIN_MEMBER);
      model.addAttribute("isBookmark", coffeeShopSVC.selectShopBookmark(shopId, loginMemberShip.getMemberId()));
      model.addAttribute("coffeeShopDetailForm", coffeeShopDetailForm);
      log.info("coffeeShopDetailForm={}", coffeeShopDetailForm);
    } else {
      model.addAttribute("coffeeShopDetailForm", coffeeShopDetailForm);
//      log.info("coffeeShopDetailForm={}", coffeeShopDetailForm);
    }

    //첨부조회
    List<UploadFile> attachFiles1 = uploadFileSVC.getFilesByCodeWithRid(coffeeShop.getBcategoryB0101(), coffeeShop.getShopId());
    List<UploadFile> attachFiles2 = uploadFileSVC.getFilesByCodeWithRid(coffeeShop.getBcategoryB0102(), coffeeShop.getShopId());
    List<UploadFile> attachFiles3 = uploadFileSVC.getFilesByCodeWithRid(coffeeShop.getBcategoryB0103(), coffeeShop.getShopId());
    List<UploadFile> attachFiles4 = uploadFileSVC.getFilesByCodeWithRid(coffeeShop.getBcategoryB0104(), coffeeShop.getShopId());
    List<UploadFile> attachFiles5 = uploadFileSVC.getFilesByCodeWithRid(coffeeShop.getBcategoryB0105(), coffeeShop.getShopId());

    if (attachFiles1.size() > 0) {
      log.info("attachFiles1={}", attachFiles1);
      model.addAttribute("attachFiles1", attachFiles1);
    }
    if (attachFiles2.size() > 0) {
      log.info("attachFiles2={}", attachFiles2);
      model.addAttribute("attachFiles2", attachFiles2);
    }
    if (attachFiles3.size() > 0) {
      log.info("attachFiles3={}", attachFiles3);
      model.addAttribute("attachFiles3", attachFiles3);
    }
    if (attachFiles4.size() > 0) {
      log.info("attachFiles4={}", attachFiles4);
      model.addAttribute("attachFiles4", attachFiles4);
    }

    if (attachFiles5.size() > 0) {
      log.info("attachFiles5={}", attachFiles5);
      model.addAttribute("attachFiles5", attachFiles5);
    }

    coffeeShopSVC.increaseViewCnt(shopId);

    String view = null;
    view = (session == null) ? "contentBeforeLogin" : "contentAfterLogin" ;

    return view;
  }

  //삭제
  @GetMapping("/{shopId}/del")
  public String del(@PathVariable Long shopId) {

    coffeeShopSVC.removeCoffeeShop(shopId);

    return "redirect:/coffeeShop/list";
  }

  //수정양식
  @GetMapping("/{shopId}/edit")
  public String editForm(
      @PathVariable Long shopId,
      HttpServletRequest request,
      HttpSession session,
      @RequestParam(required = false,defaultValue = "/") String redirectUrl,
      Model model) {

    CoffeeShop coffeeShop = coffeeShopSVC.findByShopId(shopId);

    CoffeeShopModifyForm coffeeShopModifyForm = new CoffeeShopModifyForm();
    BeanUtils.copyProperties(coffeeShop, coffeeShopModifyForm);
    model.addAttribute("coffeeShopModifyForm", coffeeShopModifyForm);

    log.info("coffeeShopModifyForm={}", coffeeShopModifyForm);

    //첨부조회
    List<UploadFile> attachFiles1 = uploadFileSVC.getFilesByCodeWithRid(coffeeShop.getBcategoryB0101(), coffeeShop.getShopId());
    List<UploadFile> attachFiles2 = uploadFileSVC.getFilesByCodeWithRid(coffeeShop.getBcategoryB0102(), coffeeShop.getShopId());
    List<UploadFile> attachFiles3 = uploadFileSVC.getFilesByCodeWithRid(coffeeShop.getBcategoryB0103(), coffeeShop.getShopId());
    List<UploadFile> attachFiles4 = uploadFileSVC.getFilesByCodeWithRid(coffeeShop.getBcategoryB0104(), coffeeShop.getShopId());
    List<UploadFile> attachFiles5 = uploadFileSVC.getFilesByCodeWithRid(coffeeShop.getBcategoryB0105(), coffeeShop.getShopId());


    if (attachFiles1.size() > 0) {
      log.info("attachFiles1={}", attachFiles1);
      model.addAttribute("attachFiles1", attachFiles1);
    }
    if (attachFiles2.size() > 0) {
      log.info("attachFiles2={}", attachFiles2);
      model.addAttribute("attachFiles2", attachFiles2);
    }
    if (attachFiles3.size() > 0) {
      log.info("attachFiles3={}", attachFiles3);
      model.addAttribute("attachFiles3", attachFiles3);
    }
    if (attachFiles4.size() > 0) {
      log.info("attachFiles4={}", attachFiles4);
      model.addAttribute("attachFiles4", attachFiles4);
    }

    if (attachFiles5.size() > 0) {
      log.info("attachFiles5={}", attachFiles5);
      model.addAttribute("attachFiles5", attachFiles5);
    }

    //회원 세션 정보
    LoginMemberShip loginMember = (LoginMemberShip) session.getAttribute(SessionConst.LOGIN_MEMBER);

    //인증성공
    //세션이 있으면 세션 반환, 없으면 새롭게 생성
    session = request.getSession(true);
    session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

    if(loginMember.getMemberEmail().equals("admin@surfer.com")) {
      return "shopModify";
    }else {
      return "redirect:"+redirectUrl;
    }
  }

  //수정처리
  @PostMapping("/{shopId}/edit")
  public String edit(
      @PathVariable Long shopId,
      @Valid @ModelAttribute CoffeeShopModifyForm coffeeShopModifyForm,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes
  ) {
    if (bindingResult.hasErrors()) {
      return "shopModify";
    }
    CoffeeShop coffeeShop = new CoffeeShop();
    BeanUtils.copyProperties(coffeeShopModifyForm, coffeeShop);
    coffeeShopSVC.modifyCoffeeShop(shopId, coffeeShop);

    coffeeShopSVC.modifyCoffeeShop(coffeeShop,shopId, coffeeShopModifyForm.getFiles1(), coffeeShopModifyForm.getFiles2(), coffeeShopModifyForm.getFiles3(), coffeeShopModifyForm.getFiles4() , coffeeShopModifyForm.getFiles5());
    log.info("들어오낭ㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇㅇ");



    redirectAttributes.addAttribute("shopId", shopId);
    return "redirect:/coffeeShop/list";
  }



  //회원전체조회
  @GetMapping("/memberlist")
  public String memberlist(Model model,
                           @RequestParam(required = false, defaultValue = "/") String redirectUrl,
                           HttpSession session
  ){

    List<MemberShip> memberAll = memberShipSVC.FindAll();
    model.addAttribute("memberAll", memberAll);

    LoginMemberShip loginMember = (LoginMemberShip) session.getAttribute(SessionConst.LOGIN_MEMBER);
    if(loginMember.getMemberEmail().equals("admin@surfer.com")) {
      return "coffeeShop/memberList";

    }
    return "redirect:"+redirectUrl;
  }


  //신고리뷰 목록
  @GetMapping("/reviewList")
  public String reviewList(Model model,
                           @RequestParam(required = false, defaultValue = "/") String redirectUrl,
                           HttpSession session){

    List<Review> reviews = reviewSVC.selectByReport();
    model.addAttribute("reviews", reviews);
    log.info("reviews={}",reviews);

    LoginMemberShip loginMember = (LoginMemberShip) session.getAttribute(SessionConst.LOGIN_MEMBER);
    if(loginMember.getMemberEmail().equals("admin@surfer.com")) {
      return "coffeeShop/reviewList";

    }
    return "redirect:"+redirectUrl;
  }

  @Configuration
  public class WebConfig implements WebMvcConfigurer {


    private String connectPath = "/attach/**";

    private String resourcePath = "file:/attach/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
      registry.addResourceHandler(connectPath)
          .addResourceLocations(resourcePath);
    }
  }

}
