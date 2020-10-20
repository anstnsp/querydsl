package com.anstn.jpa.domain.item.book;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.anstn.jpa.domain.item.Item;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@DiscriminatorValue("B")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class Book extends Item {
  
  private String author; 
  private String isbn; 
  
  // public void updateBook(Book book) {
  //   this.setName(book.getName());
  //   this.setPrice(book.getPrice());
  //   this.setQuantity(book.getQuantity());
  //   this.author = book.getAuthor();
  //   this.isbn = book.getIsbn();
  // }
}