package com.anstn.jpa.domain.order;

import java.util.List;

import com.anstn.jpa.web.dto.order.OrderSearchDTO;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;
import static com.anstn.jpa.domain.member.QMember.member;
import static com.anstn.jpa.domain.order.QOrder.order;

@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepositoryCustom {
  
  private final JPAQueryFactory queryFactory; 
  
  @Override
  public List<Order> findAll(OrderSearchDTO orderSearchDTO) {
    queryFactory.selectFrom(order)
        .join(member)
        .where(
          member.name.contains(orderSearchDTO.getMemberName()),
          eqSearchOrder(orderSearchDTO.getOrderStatus())
        )
        .fetch();

                
    return null;
  }

  private BooleanExpression eqSearchOrder(OrderStatus orderStatus) {
    if (StringUtils.isEmpty(orderStatus)) return null; 
    return order.status.eq(orderStatus);
  }

  private BooleanExpression eqMemberName(String memberName) {
    if (StringUtils.isEmpty(memberName)) return null; 
    return member.name.eq(memberName);
  }

 
}