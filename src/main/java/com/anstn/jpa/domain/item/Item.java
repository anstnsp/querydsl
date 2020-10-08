package com.anstn.jpa.domain.item;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
public abstract class Item {
  
  @Id @GeneratedValue
  @Column(name = "ITEM_ID")
  private Long id; 

  private String name; 
  private int price; 
  private int quantity; 

  @OneToMany(mappedBy = "item")
  private List<CategoryItem> categoryItem = new ArrayList<CategoryItem>(); 

  
}


