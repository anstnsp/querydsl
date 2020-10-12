package com.anstn.jpa.domain.category;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.anstn.jpa.domain.categoryitem.CategoryItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Category {

  @Id @GeneratedValue
  @Column(name = "CATEGORY_ID")
  private Long id; 

  private String name; 

  // @OneToMany(mappedBy = "parent") 
  // private List<Category> child = new ArrayList<Category>(); 
  @OneToMany(mappedBy = "category")
  private List<CategoryItem> categoryItems = new ArrayList<CategoryItem>();


}