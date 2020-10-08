package com.anstn.jpa.domain.member;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.anstn.jpa.domain.order.Order;

import org.apache.tomcat.jni.Address;

import lombok.Getter;

@Getter
@Entity
public class Member {

  @Id @GeneratedValue
  @Column(name = "MEMBER_ID")
  private Long id; 

  private String name; 

  @Embedded
  private Address address; 
  
  @OneToMany(mappedBy = "member")
  private List<Order> orders = new ArrayList<Order>(); 
  
  
}