package com.easyshopping.NEWInstance;
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


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return messageservice.find(Object);
}


@PutMapping
("/delete")
public void delete(@RequestParam(name = "id") Long id,@RequestParam(name = "member") Member member){
messageservice.delete(id,member);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return messageservice.save(Object);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return messageservice.update(Object);
}


@GetMapping
("/findPage")
public Page<Message> findPage(@RequestParam(name = "member") Member member,@RequestParam(name = "pageable") Pageable pageable){
  return messageservice.findPage(member,pageable);
}


@GetMapping
("/findDraftPage")
public Page<Message> findDraftPage(@RequestParam(name = "sender") Member sender,@RequestParam(name = "pageable") Pageable pageable){
  return messageservice.findDraftPage(sender,pageable);
}


}