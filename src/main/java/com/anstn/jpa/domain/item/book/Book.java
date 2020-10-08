package com.anstn.jpa.domain.item.book;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.anstn.jpa.domain.item.Item;

@Entity
@DiscriminatorValue("B")
public class Book extends Item {
  
  private String author; 
  private String isbn; 
  
}