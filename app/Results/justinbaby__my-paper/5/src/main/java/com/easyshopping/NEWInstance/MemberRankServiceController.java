package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MemberRankServiceController {

 private MemberRankService memberrankservice;


@GetMapping
("/findDefault")
public MemberRank findDefault(){
  return memberrankservice.findDefault();
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return memberrankservice.findAll(Object);
}


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return memberrankservice.find(Object);
}


@GetMapping
("/findList")
public Object findList(@RequestParam(name = "Object") Object Object){
  return memberrankservice.findList(Object);
}


}