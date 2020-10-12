package com.anstn.jpa.domain.member;

import java.util.List;

import com.anstn.jpa.exception.ExistsMemberException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {

  private final MemberRepository memberRepository; 
  
  @Override
  public Long join(Member member) {
    validateDuplicateMember(member);
    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
    List<Member> findMembers = memberRepository.findByName(member.getName());
    if (!findMembers.isEmpty()) throw new ExistsMemberException("이미 존재하는 회원입니다.");
  }
}