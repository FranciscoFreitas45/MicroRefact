package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MemberServiceController {

 private MemberService memberservice;


@GetMapping
("/getCurrent")
public Member getCurrent(){
  return memberservice.getCurrent();
}


@GetMapping
("/equals")
public Object equals(@RequestParam(name = "Object") Object Object){
  return memberservice.equals(Object);
}


@PutMapping
("/update")
public void update(@RequestParam(name = "member") Member member,@RequestParam(name = "modifyPoint") Integer modifyPoint,@RequestParam(name = "modifyBalance") BigDecimal modifyBalance,@RequestParam(name = "depositMemo") String depositMemo,@RequestParam(name = "operator") Admin operator){
memberservice.update(member,modifyPoint,modifyBalance,depositMemo,operator);
}


@GetMapping
("/usernameExists")
public boolean usernameExists(@RequestParam(name = "username") String username){
  return memberservice.usernameExists(username);
}


@GetMapping
("/findByUsername")
public Member findByUsername(@RequestParam(name = "username") String username){
  return memberservice.findByUsername(username);
}


@GetMapping
("/find")
public Object find(@RequestParam(name = "Object") Object Object){
  return memberservice.find(Object);
}


@GetMapping
("/count")
public Object count(@RequestParam(name = "Object") Object Object){
  return memberservice.count(Object);
}


@GetMapping
("/findPurchaseList")
public List<Object[]> findPurchaseList(@RequestParam(name = "beginDate") Date beginDate,@RequestParam(name = "endDate") Date endDate,@RequestParam(name = "count") Integer count){
  return memberservice.findPurchaseList(beginDate,endDate,count);
}


}