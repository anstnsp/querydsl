package com.anstn.jpa.domain.category;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.annotations.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.anstn.jpa.domain.categoryitem.CategoryItem;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Category {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "CATEGORY_ID")
  private Long id; 

  private String name; 

  // @OneToMany(mappedBy = "parent") 
  // private List<Category> child = new ArrayList<Category>(); 

  @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
  private List<CategoryItem> categoryItems = new ArrayList<CategoryItem>();


}