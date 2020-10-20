package com.anstn.jpa.web.controller;

import java.util.List;
import java.util.Optional;

import com.anstn.jpa.domain.member.Member;
import com.anstn.jpa.exception.ExistsMemberException;
import com.anstn.jpa.service.MemberService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

  private final MemberService memberService; 
  
  //회원가입
  @PostMapping("/join")
  public String memberJoin(@RequestBody Member member) {
    memberService.join(member); 
    return "success";
  }

  //회원하나 조회
  @GetMapping("/member/{memberId}")
  public Member findMember(@PathVariable("memberId") Long memberId) {
    Optional<Member> maybeMember = memberService.findOne(memberId);
    return maybeMember.orElseThrow(() -> new ExistsMemberException("해당 회원은 존재하지 않습니다."));
  }

  //회원리스트 
  @GetMapping("/members")
  public List<Member> findAllMember() {
    return memberService.findMembers();
  }

  
}