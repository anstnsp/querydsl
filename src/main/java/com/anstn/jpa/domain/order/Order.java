package com.anstn.jpa.domain.order;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.anstn.jpa.domain.member.Member;

import lombok.Getter;

@Getter
@Entity
@Table(name = "ORDERS")
public class Order {
  
  @Id @GeneratedValue
  @Column(name = "ORDER_ID")
  private Long id; 
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "MEMBER_ID")
  private Member member; 
  
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) 
  private List<OrderItem> orderItem = new ArrayList<OrderItem>(); 
  
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
  @JoinColumn(name = "DELIVERY_ID")
  private Delivery delivery; //배송정보

  private LocalDateTime orderDate; //주문시간 
  
  @Enumerated(EnumType.STRING)
  private OrderStatus status; //주문상태 

  //연관관계 메소드 // 
  public void setMember(Member membeer) {
    this.member = member; 
    member.getOrders().add(this); 
  }

  public void addOrderItem(OrderItem orderItem) {
    orderItems.add(orderItem); 
    orderItem.setOrder(this); 
  }
  
  public void setDelivery(Delivery delivery) {
    this.delivery = delivery; 
    delivery.setOrder(this); 
  }
}