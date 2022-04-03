package com.cym.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MessageUtilsController {

 private MessageUtils messageutils;


@GetMapping
("/get")
public String get(@RequestParam(name = "msgKey") String msgKey){
  return messageutils.get(msgKey);
}


}