package com.anstn.jpa.domain.item.album;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.anstn.jpa.domain.item.Item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("A")
public class Album extends Item {
  
  private String artist; 
  private String etc;
  
}