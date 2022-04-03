package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TransferServiceController {

 private TransferService transferservice;


@GetMapping
("/findPage")
public Page<Transfer> findPage(@RequestParam(name = "fromMember") Member fromMember,@RequestParam(name = "toMember") Member toMember,@RequestParam(name = "method") Method method,@RequestParam(name = "pageable") Pageable pageable){
  return transferservice.findPage(fromMember,toMember,method,pageable);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return transferservice.findAll(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return transferservice.delete(Object);
}


}