package com.anstn.jpa.service;

import java.util.List;

import com.anstn.jpa.domain.member.MemberRepository;
import com.anstn.jpa.domain.order.Order;
import com.anstn.jpa.domain.order.OrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class OrderService {

  private final OrderRepository orderRepository; 
  private final MemberRepository memberRepository; 
  private final ItemService itemService; 

  //주문 
  public Long order(Long memberId, Long itemId, int count) {
    
    //엔티티조회 
    Optional<Member> member = memberRepository.findById(memberId); 
    Optional<Item> item = itemService.findOne(itemId);

    //배송정보 생성 
    Delivery delivery = new Delivery(member.get().getAddress())
    //주문상품생성 
    OrderItem orderItem = OrderItem.createOrderItem(item.get(), item.get().getPrice(), count);
    //주문 생성 
    Order order = Order.createOrder(member.get(), delivery, orderItem);
    //주문저장 
    orderRepository.save(order); 
    return order.getId();
    
  }

  //주문취소 
  public void cancelOrder(Long orderId) {
    //주문 엔티티 조회 
    Order order = orderRepository.getOne(orderId);
    order.cancel();;
  }

  //주문검색 
  public List<Order> findOrders(OrderSearch orderSearch) {
    return orderRepository.findAll(orderSearch);
  }
}