package com.anstn.jpa.domain.delivery;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Delivery {

  @Id @GeneratedValue
  @Column(name ="DELEVERY_ID") 
  private Long id; 

  @OneToOne(mappedBy = "delivery")
  private Order order; 

  @Embedded
  private Address address; 
  
  @Enumerated(EnumType.STRING)
  private DeliveryStatus status; //enum [READY(준비), COMP(배송)]
  
}