package com.anstn.jpa.web.controller;

import java.util.List;
import java.util.Optional;

import com.anstn.jpa.domain.item.Item;
import com.anstn.jpa.domain.item.book.Book;
import com.anstn.jpa.service.ItemService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService; 


  @PostMapping("/items/new")
  public String create(Book item) {
    System.out.println(item.getAuthor());
    System.out.println(item.getIsbn());

    itemService.saveItem(item);
    return "success";     
  }
  
  @GetMapping("/items")
  public List<Item> list() {
    return itemService.findItems();
  }

  @PutMapping("/items/{itemId}/edit")
  public String updateItem(@PathVariable("itemId") Long itemId, Book item) {
    
    Optional<Item> originItem = itemService.findOne(itemId);
    // originItem.map(Item::getId).orElse((long) 0);
    Item aa = originItem.orElseThrow(() -> new Exception()));
    itemService.saveItem(item);
    return "update";
  }
}