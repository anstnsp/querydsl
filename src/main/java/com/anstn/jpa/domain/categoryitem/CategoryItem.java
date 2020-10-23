package com.anstn.jpa.domain.categoryitem;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.anstn.jpa.domain.category.Category;
import com.anstn.jpa.domain.item.Item;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "CATEGORY_ITEM")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class CategoryItem {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CATEGORY_ITEM_ID")
  private Long id; 

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name ="CATEGORY_ID")
  private Category category; 

  @ManyToOne(fetch = FetchType.EAGER)
  @JoinColumn(name = "ITEM_ID") 
  private Item item; 

  
}