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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.anstn.jpa.domain.delivery.Delivery;
import com.anstn.jpa.domain.delivery.DeliveryStatus;
import com.anstn.jpa.domain.member.Member;
import com.anstn.jpa.domain.orderitem.OrderItem;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "ORDERS")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Order {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ORDER_ID")
  private Long id; 
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "MEMBER_ID")
  private Member member; 
  
  @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY) 
  private List<OrderItem> orderItems = new ArrayList<OrderItem>(); 
  
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "DELIVERY_ID")
  private Delivery delivery; //배송정보

  private LocalDateTime orderDate; //주문시간 
  
  @Enumerated(EnumType.STRING)
  private OrderStatus status; //주문상태 

  //연관관계 메소드 // 
  public void setMember(Member member) {
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

  //주문생성 메소드 
  public static Order createOrder(Member member, Delivery delivery, OrderItem... orderItems) {
    Order order = new Order(); 
    order.setMember(member);
    order.setDelivery(delivery);
    for (OrderItem orderItem : orderItems) {
      order.addOrderItem(orderItem);
    }
    order.setStatus(OrderStatus.ORDER);
    order.setOrderDate(LocalDateTime.now());
    return order; 
  }

  //==비지니스 로직==//
  //주문취소 
  public void cancel() {
    if (delivery.getStatus() == DeliveryStatus.COMP) throw new RuntimeException("이미 배송완료");
    this.setStatus(OrderStatus.CANCEL);
    for (OrderItem orderItem : orderItems) {
      orderItem.cancel(); 
    }
  }

  //==조회로직==//
  //전체 주문 가격 조회 
  public int getTotalPrice() {
    int totalPrice = 0; 
    for (OrderItem orderItem : orderItems) {
      totalPrice += orderItem.getTotalPrice(); 
    }
    return totalPrice;
  }
  
}