package com.anstn.jpa.domain.orderitem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.anstn.jpa.domain.item.Item;
import com.anstn.jpa.domain.order.Order;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "ORDER_ITEM")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class OrderItem {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ORDER_ITEM_ID")
  private Long id;  
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ITEM_ID")
  private Item item; //주문상품 
  
  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ORDER_ID")
  private Order order;  //주문 

  private int orderPrice;  //주문가격
  private int count; //주문수량

  //생성메소드 
  public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
    OrderItem orderItem = new OrderItem(); 
    orderItem.setItem(item);
    orderItem.setOrderPrice(orderPrice);
    orderItem.setCount(count);

    item.removeStock(count);
    return orderItem; 
  }

  //비지니스로직 
  //주문취소 
  public void cancel() {
    getItem().addStock(count);
  }

  //조회로직 
  //주문상품 전체 가격 조회 
  public int getTotalPrice() {
    return getOrderPrice() * getCount(); 
  }
  
}