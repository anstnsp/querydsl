package com.anstn.jpa.domain.delivery;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.anstn.jpa.domain.embeded.Address;
import com.anstn.jpa.domain.order.Order;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Delivery {

  public Delivery(Address address) {
    this.address = address; 
  }
  public Delivery(){}
  // public Delivery(Address address2) {
	// }

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