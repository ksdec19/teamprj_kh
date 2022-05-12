package com.kh.cafesurfer.domain.memberShip.dao;

import com.kh.cafesurfer.domain.memberShip.MemberShip;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
@RequiredArgsConstructor
class MemberShipDAOImplTest {

  private final MemberShipDAO memberShipDAO;

  @Test
  void findEmailByTel() {
    MemberShip memberShip = new MemberShip();
    String memberName = "김무년";
    String memberTel = "01088122711";

    String emailByTel = memberShipDAO.findEmailByTel(memberName, memberTel);


  }

  @Test
  void findPwByEmail() {
  }
}