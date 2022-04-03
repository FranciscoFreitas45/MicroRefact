package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MessageServiceController {

 private MessageService messageservice;


@GetMapping
("/count")
public Long count(@RequestParam(name = "member") Member member,@RequestParam(name = "read") Boolean read){
  return messageservice.count(member,read);
}


}