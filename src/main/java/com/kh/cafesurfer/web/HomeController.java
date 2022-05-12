package com.kh.cafesurfer.web;

import com.kh.cafesurfer.domain.CoffeeShop.CoffeeShop;
import com.kh.cafesurfer.domain.CoffeeShop.dao.CoffeeShopFilterCondition;
import com.kh.cafesurfer.domain.CoffeeShop.svc.CoffeeShopSVC;
import com.kh.cafesurfer.domain.common.paging.FindCriteria;
import com.kh.cafesurfer.web.form.coffeeShop.CoffeeShopListForm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {

  private final CoffeeShopSVC coffeeShopSVC;

  @Autowired
  @Qualifier("fc5") //동일한 타입의 객체가 여러개있을때 빈이름을 명시적으로 지정해서 주입받을때
  private FindCriteria fc;

  @RequestMapping({"/",
      "/coffeeShop/list",
      "/coffeeShop/list/{reqPage}",
      "/coffeeShop/list/{reqPage}//",
      "/coffeeShop/list/{reqPage}/{searchType}/{keyword}"})
  public String home(
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


    String view = null;
    HttpSession session = request.getSession(false);
    view = (session == null) ? "beforeLogin" : "afterLogin";

    return view;
  }

}
