package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BankServiceController {

 private BankService bankservice;


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return bankservice.find(Object);
}


@GetMapping
("/findListByMember")
public List<Bank> findListByMember(@RequestParam(name = "member") Member member){
  return bankservice.findListByMember(member);
}


}