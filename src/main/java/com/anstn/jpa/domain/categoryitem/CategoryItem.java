package com.anstn.jpa.domain.categoryitem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.anstn.jpa.domain.category.Category;
import com.anstn.jpa.domain.item.Item;

@Entity
@Table(name = "CATEGORY_ITEM")
public class CategoryItem {

  @Id @GeneratedValue
  @Column(name = "CATEGORY_ITEM_ID")
  private Long id; 

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name ="CATEGORY_ID")
  private Category category; 

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "ITEM_ID") 
  private Item item; 

  
}