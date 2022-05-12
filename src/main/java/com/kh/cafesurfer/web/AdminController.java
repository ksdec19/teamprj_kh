package com.kh.cafesurfer.web;

import com.kh.cafesurfer.domain.CoffeeShop.CoffeeShop;
import com.kh.cafesurfer.domain.CoffeeShop.dao.CoffeeShopFilterCondition;
import com.kh.cafesurfer.domain.CoffeeShop.svc.CoffeeShopSVC;
import com.kh.cafesurfer.domain.common.code.CodeDAO;
import com.kh.cafesurfer.domain.common.file.svc.UploadFileSVC;
import com.kh.cafesurfer.domain.common.paging.FindCriteria;
import com.kh.cafesurfer.domain.memberShip.svc.MemberShipSVC;
import com.kh.cafesurfer.domain.review.Review;
import com.kh.cafesurfer.domain.review.svc.ReviewSVC;
import com.kh.cafesurfer.web.form.coffeeShop.CoffeeShopListForm;
import com.kh.cafesurfer.web.form.coffeeShop.parking;
import com.kh.cafesurfer.web.form.coffeeShop.allday;
import com.kh.cafesurfer.web.form.login.LoginMemberShip;
import com.kh.cafesurfer.web.form.memberShip.OutForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {

  private final CoffeeShopSVC coffeeShopSVC;
  private final MemberShipSVC memberShipSVC;
  private final ReviewSVC reviewSVC;
  private final CodeDAO codeDAO;
  private final UploadFileSVC uploadFileSVC;


  @ModelAttribute("parking")
  public parking[] parking() {

    return parking.values();
  }

  @ModelAttribute("allday")
  public allday[] allday() {

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

  // 목록
  @GetMapping({"/coffeeShop/list",
      "/coffeeShop/list/{reqPage}",
      "/coffeeShop/list/{reqPage}//"
  })
  public String listAndReqPage(
      @PathVariable(required = false) Optional<Integer> reqPage,
      @PathVariable(required = false) Optional<String> searchType,
      @PathVariable(required = false) Optional<String> keyword,
      HttpServletRequest request,
      Model model) {
    log.info("/list 요청됨{},{},{}", reqPage, searchType, keyword);

    //FindCriteria 값 설정
    fc.getRc().setReqPage(reqPage.orElse(1)); //요청페이지, 요청없으면 1
    fc.setSearchType(searchType.orElse(""));  //검색유형
    fc.setKeyword(keyword.orElse(""));        //검색어

    List<CoffeeShop> list = null;

    //검색어 있음
    if (searchType.isPresent() && keyword.isPresent()) {
      CoffeeShopFilterCondition coffeeShopFilterCondition = new CoffeeShopFilterCondition(
          fc.getRc().getStartRec(), fc.getRc().getEndRec(),
          searchType.get(),
          keyword.get());
      fc.setTotalRec(coffeeShopSVC.totalCount(coffeeShopFilterCondition));
      fc.setSearchType(searchType.get());
      fc.setKeyword(keyword.get());
      list = coffeeShopSVC.findAll(coffeeShopFilterCondition);

      //검색어 없음
    } else {
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
    model.addAttribute("fc", fc);

    log.info("list={}", list);


    HttpSession session = request.getSession(false);
    if (session != null) {
      LoginMemberShip loginMemberShip = (LoginMemberShip) session.getAttribute(SessionConst.LOGIN_MEMBER);
      if (loginMemberShip.getMemberEmail().equals("admin@surfer.com")) {
        return "coffeeShop/list";
      }
    }
    return "redirect:/";
  }

  // 회원 강퇴(탈퇴)
  @PostMapping("/{memberEmail}/memberDel")
  public String out(
      @ModelAttribute OutForm outForm){

    //탈퇴로직 수행
    //회원정보 삭제
    memberShipSVC.removeMember(outForm.getMemberEmail());
    return "redirect:/coffeeShop/memberlist";
  }

  //신고된 리뷰 삭제
  @PostMapping("/{reviewId}/reviewDel")
  public String deleteReview(
      @ModelAttribute Review review){
    reviewSVC.removeReview(review.getReviewId(), review.getShopId());

    return "redirect:/coffeeShop/reviewList";
  }


}
