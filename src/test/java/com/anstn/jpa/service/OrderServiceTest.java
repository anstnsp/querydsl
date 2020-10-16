package com.anstn.jpa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntPredicate;
import java.util.function.Predicate;
import java.util.function.Supplier;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.anstn.jpa.domain.embeded.Address;
import com.anstn.jpa.domain.item.Item;
import com.anstn.jpa.domain.item.book.Book;
import com.anstn.jpa.domain.member.Member;
import com.anstn.jpa.domain.order.Order;
import com.anstn.jpa.domain.order.OrderRepository;
import com.anstn.jpa.domain.order.OrderStatus;
import com.anstn.jpa.exception.NotEnoughStockException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class OrderServiceTest {

  @PersistenceContext
  EntityManager em; 

  @Autowired
  OrderService orderService; 
  @Autowired
  OrderRepository orderRepository; 

  @Test
  public void 상품주문() throws Exception {
    //given
    Member member = createMember(); 
    Item item = createBook("자바의정석", 30000, 10);
    int orderCount = 2; //주문수량  
    
    //when
    Long orderId = orderService.order(member.getId(), item.getId(), orderCount); 
    
    //then
    Optional<Order> getOrder = orderRepository.findById(orderId); 
    Order order = getOrder.get();
    assertEquals(OrderStatus.ORDER, order.getStatus(), "상품 주문시 상태는 ORDER"); 
    assertEquals(1,order.getOrderItems().size() , "주문한 상품종류수가 일치해야한다.");   //기대값, 실제값, 메세지 
    assertEquals(60000, order.getTotalPrice(), "주문가격은 가격*수량임.");
    assertEquals(8, item.getQuantity(), "재고가 줄어야댐.");
  }

  @Test
  public void 상품주문_재고초과테스트() throws Exception {
        //given
        Member member = createMember(); 
        Item item = createBook("자바의정석", 30000, 10);
        int orderCount = 20; //주문수량  
        
        //when 
        Throwable exception = assertThrows(NotEnoughStockException.class, () ->{
          orderService.order(member.getId(), item.getId(), orderCount); 
        });

        //then 
        assertEquals(exception.getMessage(), "재고가 부족합니다.");
  }

  @Test
  public void 주문취소() {
    //given
    Member member = createMember(); 
    Item item = createBook("책이름", 30000, 10); //이름, 가격, 재고 
    int orderCount = 2; 

    Long orderId = orderService.order(member.getId(), item.getId(), orderCount);
    //when
    orderService.cancelOrder(orderId);

    //then
    Optional<Order> getOrder = orderRepository.findById(orderId);
    assertEquals(OrderStatus.CANCEL, getOrder.get().getStatus(), "주문 취소시 상태는 CANCEL이다");
    assertEquals(10, item.getQuantity(), "주문이 취소된 상품은 재고가 그만큼 증가해야 한다.");


  }


  private Member createMember() {
    Member member = new Member(); 
    member.setName("문수");
    member.setAddress(new Address("서울", "강가", "123-123"));
    em.persist(member);
    return member; 
  }
    
  private Book createBook(String name, int price, int quantity) {
    Book book = new Book(); 
    book.setName(name);
    book.setQuantity(quantity);
    book.setPrice(price);
    em.persist(book);
    return book; 
  }

}