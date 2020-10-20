package com.anstn.jpa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.anstn.jpa.domain.item.Item;
import com.anstn.jpa.domain.item.ItemRepository;
import com.anstn.jpa.domain.item.book.Book;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Transactional
@SpringBootTest
public class ItemServiceTest {

  @PersistenceContext
  EntityManager em; 

  
  @Autowired
  private ItemRepository itemRepository; 

  @Test
  public void 아템_저장() {
    //given
    Item item = createItem("asdf", 1000, 20);
    //when
    Item savedItem = itemRepository.save(item); 
    //then 
    Optional<Item> findedItem = itemRepository.findById(1L);
    assertEquals("asdf", findedItem.get().getName(), "name 같음");
  }

  @Test
  public void 아템_리스트() {
    //given
    createItem("앨범하나", 4000, 30);
    createItem("앨범두울", 2000, 10); 
    //when
    List<Item> itemList = itemRepository.findAll();
    //then 
    assertEquals(2, itemList.size(), "길이 2개");
  }

  @Test
  public void 하나조회() {
    //given
    Item item = createItem("책하나", 10000, 10);
    //when
    Optional<Item> findedItem = itemRepository.findById(1L);
    //then 
    assertEquals("책하나", findedItem.get().getName(), "책이름 같음");
  }


  public Item createItem(String name, int price, int quantity) {
    Book book = new Book(); 
    book.setName(name);
    book.setQuantity(quantity);
    book.setPrice(price);
    em.persist(book);
    return book; 
  }




}