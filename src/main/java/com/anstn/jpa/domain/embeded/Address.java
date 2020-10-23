package com.anstn.jpa.domain.embeded;

import javax.persistence.Embeddable;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Address {

  private String city;
  private String street; 
  private String zipcode; 

}