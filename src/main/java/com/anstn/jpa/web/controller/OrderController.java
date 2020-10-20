package com.anstn.jpa.web.controller;

import java.util.List;

import com.anstn.jpa.domain.order.Order;
import com.anstn.jpa.service.ItemService;
import com.anstn.jpa.service.MemberService;
import com.anstn.jpa.service.OrderService;
import com.anstn.jpa.web.dto.order.OrderSearchDTO;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OrderController {

  private final OrderService orderService; 
  private final MemberService memberService; 
  private final ItemService itemService; 


  //상품 주문하기
  @PostMapping("/order")
  public String order(@RequestParam("memberId") Long memberId,
                      @RequestParam("itemId") Long itemId,
                      @RequestParam("count") int count) {

    orderService.order(memberId, itemId, count);
    return "주문완료!!";  
  }

  //주문 검색 
  @GetMapping("/orders")
  public List<Order> findOrders(@RequestBody OrderSearchDTO orderSearchDTO) {
    System.out.println("orderSearchDTO.getMemberName():"+orderSearchDTO.getMemberName());
    System.out.println("orderSearchDTO.getOrderStatus():"+orderSearchDTO.getOrderStatus());
    return orderService.findOrders(orderSearchDTO);
  }


}