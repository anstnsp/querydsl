package com.anstn.jpa.web.controller;

import java.util.List;
import java.util.Optional;

import com.anstn.jpa.domain.item.Item;
import com.anstn.jpa.domain.item.book.Book;
import com.anstn.jpa.exception.NoExistsItemException;
import com.anstn.jpa.service.ItemService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ItemController {

  private final ItemService itemService; 


  //아템 등록 
  @PostMapping("/items/new")
  public String create(@RequestBody Book item) {
    System.out.println("item.getAuthor(): "+item.getAuthor());
    System.out.println(item.getIsbn());

    itemService.saveItem(item);
    return "success";     
  }
  
  //아템리스트
  @GetMapping("/items")
  public List<Item> list() {
    return itemService.findItems();
  }

  //아템하나 조회
  @GetMapping("/item/{itemId}")
  public Item findOneItem(@PathVariable("itemId") Long itemId) {
    Optional<Item> maybeItem = itemService.findOne(itemId);
    return maybeItem.orElseThrow(() -> new NoExistsItemException("해당 item은 존재하지 않습니다."));
  }

  //아템 수정
  @PutMapping("/items/{itemId}/edit")
  public String updateItem(@PathVariable("itemId") Long itemId,@RequestBody Book item) throws Exception {
    
    itemService.updateItem(itemId, item);
    
    return "update";
  }

}