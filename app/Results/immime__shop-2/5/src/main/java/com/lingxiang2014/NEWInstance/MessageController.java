package com.lingxiang2014.NEWInstance;
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


@GetMapping
("/warn")
public Message warn(@RequestParam(name = "content") String content,@RequestParam(name = "args") Object args){
  return message.warn(content,args);
}


}