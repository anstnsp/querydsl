package com.anstn.jpa.domain.item.movie;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.anstn.jpa.domain.item.Item;

@Entity
@DiscriminatorValue("M")
public class Movie extends Item {

  private String director; 
  private String actor; 
}