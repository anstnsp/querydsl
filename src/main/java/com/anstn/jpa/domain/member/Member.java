package com.anstn.jpa.domain.member;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.anstn.jpa.domain.embeded.Address;
import com.anstn.jpa.domain.order.Order;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Member {

  @Id @GeneratedValue
  @Column(name = "MEMBER_ID")
  private Long id; 

  @Column(name = "NAME", unique = true)
  private String name; 

  @Embedded
  private Address address; 
  
  @OneToMany(mappedBy = "member")
  private List<Order> orders = new ArrayList<Order>(); 
  
  
}