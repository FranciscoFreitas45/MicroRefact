package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MessageController {

 private Message message;


@GetMapping
("/error")
public Message error(@RequestParam(name = "content") String content,@RequestParam(name = "args") Object args){
  return message.error(content,args);
}


@GetMapping
("/success")
public Message success(@RequestParam(name = "content") String content,@RequestParam(name = "args") Object args){
  return message.success(content,args);
}


}