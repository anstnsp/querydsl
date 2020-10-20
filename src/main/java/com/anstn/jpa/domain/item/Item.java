package com.anstn.jpa.domain.item;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

import com.anstn.jpa.domain.categoryitem.CategoryItem;
import com.anstn.jpa.domain.orderitem.OrderItem;
import com.anstn.jpa.exception.NotEnoughStockException;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DTYPE")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public abstract class Item {
  
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ITEM_ID")
  private Long id; 

  private String name; 
  private int price;
  private int quantity;

  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<CategoryItem> categoryItems = new ArrayList<CategoryItem>(); 

  @OneToMany(mappedBy = "item", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<OrderItem> orderItems = new ArrayList<OrderItem>(); 
  

  public void addStock(int quantity) {
    this.quantity += quantity; 
  }

  public void removeStock(int quantity) {
    int restStock = this.quantity - quantity; 
    if (restStock < 0) throw new NotEnoughStockException("재고가 부족합니다.");
    this.quantity = restStock; 
  }
  
  public void update(Item item) {
    this.name = item.name;
    this.price = item.price;
    this.quantity = item.quantity;   
  }

}


