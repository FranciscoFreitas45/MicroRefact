package com.nhnent.forward.mybatistojpa.controller;
 import com.nhnent.forward.mybatistojpa.model.Item;
import com.nhnent.forward.mybatistojpa.model.Page;
import com.nhnent.forward.mybatistojpa.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/items")
public class ItemController {

@Autowired
 private ItemService itemService;


@GetMapping("")
public List<Item> getItems(int page){
    if (page < 1) {
        page = 1;
    }
    return itemService.getItems(page, Page.PAGE_SIZE);
}


@DeleteMapping("/{itemId}")
public void deleteItem(Long itemId){
    itemService.deleteItem(itemId);
}


@PostMapping("")
public Item createItem(Item item){
    return itemService.createItem(item);
}


@GetMapping("/{itemId}")
public Item getItem(Long itemId){
    return itemService.getItem(itemId);
}


@PutMapping("/{itemId}")
public Item updateItem(Long itemId,Item item){
    item.setItemId(itemId);
    return itemService.updateItem(item);
}


}