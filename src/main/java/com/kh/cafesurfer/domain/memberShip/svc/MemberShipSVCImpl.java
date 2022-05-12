package com.kh.cafesurfer.domain.memberShip.svc;

import com.kh.cafesurfer.domain.memberShip.MemberShip;
import com.kh.cafesurfer.domain.memberShip.dao.MemberShipDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberShipSVCImpl implements MemberShipSVC{

  private final MemberShipDAO memberShipDAO;

  // 가입
  @Override
  public MemberShip addMember(MemberShip memberShip) {
    return memberShipDAO.insertMember(memberShip);
  }

  // 수정
  @Override
  public void modifyMember(MemberShip memberShip) {
    memberShipDAO.updateMember(memberShip);
  }

  // 조회 by email
  @Override
  public MemberShip memberFindByEmail(String memberEmail) {
    return memberShipDAO.selectMemberByEmail(memberEmail);
  }

  // 조회 by id
  @Override
  public MemberShip memberFindByMemberId(Long memberId) {
    return memberShipDAO.selectMemberByMemberId(memberId);
  }

  // 전체조회
  @Override
  public List<MemberShip> FindAll() {
    return memberShipDAO.selectAll();
  }

  // 삭제
  @Override
  public void removeMember(String memberEmail) {
    memberShipDAO.deleteMember(memberEmail);
  }

  // 아이디 유무 체크
  @Override
  public boolean existMemberByEmail(String memberEmail) {
    return memberShipDAO.existMemberByEmail(memberEmail);
  }

  // 전화번호 유무 체크
  @Override
  public boolean existMemberByTel(String memberTel) {return memberShipDAO.existMemberByTel(memberTel);}

  // 로그인 여부 체크
  @Override
  public MemberShip login(String memberEmail, String memberPasswd) {
    return memberShipDAO.login(memberEmail, memberPasswd);
  }

  // 비밀번호 일치 여부 체크
  @Override
  public boolean isMember(String memberEmail, String memberPasswd) {
    return memberShipDAO.isMember(memberEmail, memberPasswd);
  }

  // 아이디 찾기
  @Override
  public String findEmailByTel(String memberName, String memberTel) {
    return memberShipDAO.findEmailByTel(memberName, memberTel);
  }

  // 비밀번호 찾기
  @Override
  public String findPwByEmail(String memberName, String memberTel,String memberEmail) {
    return memberShipDAO.findPwByEmail(memberName, memberTel, memberEmail);
  }

}
