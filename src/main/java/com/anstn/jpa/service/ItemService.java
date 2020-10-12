package com.anstn.jpa.service;

import java.util.List;
import java.util.Optional;

import com.anstn.jpa.domain.item.Item;
import com.anstn.jpa.domain.item.ItemRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Service
public class ItemService {

  private final ItemRepository itemRepository; 

  public void saveItem(Item item) {
    itemRepository.save(item); 
  }

  public List<Item> findItems() {
    return itemRepository.findAll();
  }

  public Optional<Item> findOne(Long itemId) {
    return itemRepository.findById(itemId); 
  }
  
  
}