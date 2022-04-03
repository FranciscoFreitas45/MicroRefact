package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class ReceiverServiceController {

 private ReceiverService receiverservice;


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return receiverservice.save(Object);
}


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return receiverservice.find(Object);
}


@GetMapping
("/findPage")
public Page<Receiver> findPage(@RequestParam(name = "member") Member member,@RequestParam(name = "pageable") Pageable pageable){
  return receiverservice.findPage(member,pageable);
}


@GetMapping
("/update")
public Object update(@RequestParam(name = "Object") Object Object){
  return receiverservice.update(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return receiverservice.delete(Object);
}


}