package com.anstn.jpa.domain.member;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> , MemberRepositoryCustom{

  public List<Member> findByName(String name); 
}