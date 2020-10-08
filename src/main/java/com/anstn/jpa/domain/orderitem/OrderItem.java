package com.anstn.jpa.domain.orderitem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {
  
  @Id @GeneratedValue
  @Column(name = "ORDER_ITEM_ID")
  private Long id; 
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ITEM_ID")
  private Item item; //주문상품 
  
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(NAME ="ORDER_ID")
  private Order order;  //주문 

  private int orderPrice; 
  private int count; 
  
}