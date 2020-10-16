package com.anstn.jpa.domain.order;

import java.util.List;

import com.anstn.jpa.web.dto.order.OrderSearchDTO;

public interface OrderRepositoryCustom {
  List<Order> findAll (OrderSearchDTO orderSearchDTO);
}