package com.anstn.jpa.service;

import java.util.List;
import java.util.Optional;

import com.anstn.jpa.domain.item.Item;
import com.anstn.jpa.domain.item.ItemRepository;
import com.anstn.jpa.domain.item.book.Book;
import com.anstn.jpa.exception.NoExistsItemException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ItemService {

  private final ItemRepository itemRepository; 


  public void saveItem(Item item) {
    Item item1 = itemRepository.save(item); 
    System.out.println("item1:"+item1);
    System.out.println("item1.getName():"+item1.getName());
    System.out.println("item1.getPrice():"+item1.getPrice());
    System.out.println("item1.getQuantity():"+item1.getQuantity());
    
  }

  public List<Item> findItems() {
    return itemRepository.findAll();
  }

  public Optional<Item> findOne(Long itemId) {
    return itemRepository.findById(itemId); 
  }

  public void updateItem(Long itemId, Book item) {
    Optional<Item> originItem = itemRepository.findById(itemId);
    Item aa = originItem.orElseThrow( () -> new NoExistsItemException("해당 item은 존재하지 않습니다."));
    aa.update(item);
  }
  
  
}