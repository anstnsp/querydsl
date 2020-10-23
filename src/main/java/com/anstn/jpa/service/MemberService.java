package com.anstn.jpa.service;

import java.util.List;
import java.util.Optional;

import com.anstn.jpa.domain.member.Member;
import com.anstn.jpa.domain.member.MemberRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

  private final MemberRepository memberRepository; 
  
  //회원가입 
  public Long join(Member member) {
    return memberRepository.join(member);
  }

  //전체회원 조회 
  public List<Member> findMembers() {
    return memberRepository.findAll();
  }
  
  //회원한명 조회 
  public Optional<Member> findOne(Long memberId) {
    return memberRepository.findById(memberId);
  }
  
  //회원삭제 
  public void deleteMember(Long memberId) {
   memberRepository.deleteById(memberId); 
  }

}