package com.anstn.jpa.web.dto.order;

import com.anstn.jpa.domain.order.OrderStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderSearchDTO {

  private String memberName; //회원이름
  private OrderStatus orderStatus; //주문상태 [ORDER, CANCEL]

  
}