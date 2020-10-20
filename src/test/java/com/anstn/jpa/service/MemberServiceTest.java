package com.anstn.jpa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.anstn.jpa.domain.member.Member;
import com.anstn.jpa.domain.member.MemberRepository;
import com.anstn.jpa.exception.ExistsMemberException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
public class MemberServiceTest {

  @Autowired
  MemberService memberService; 
  @Autowired
  MemberRepository memberRepository; 

  @Test
  public void 회원가입() throws Exception {
    
    //given 
    Member member = new Member(); 
    member.setName("문수");
    //when 
    Long saveId = memberService.join(member); 
    //then 
    System.out.println(memberRepository.findByName("문수").get(0).getName());
    assertEquals(member, memberRepository.findById(saveId).get());
    assertEquals("문수", memberRepository.findByName("문수").get(0).getName()); 
  }

  @Test
  public void 중복_회원예외() {
    //given
    Member member1 = new Member(); 
    member1.setName("문수");
    
    Member member2 = new Member(); 
    member2.setName("문수");

    //when 
    memberService.join(member1);
    // memberService.join(member2);

    //then 
    Throwable exception = assertThrows(ExistsMemberException.class, () -> {
      // memberService.join(member1);
     memberService.join(member2);
    });
    System.out.println(exception.getMessage());
    assertEquals(exception.getMessage(), "이미 존재하는 회원입니다.");

  }
}