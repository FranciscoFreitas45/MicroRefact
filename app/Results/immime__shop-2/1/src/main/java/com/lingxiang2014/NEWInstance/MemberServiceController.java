package com.lingxiang2014.NEWInstance;
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
("/findByNumber")
public Member findByNumber(@RequestParam(name = "number") String number){
  return memberservice.findByNumber(number);
}


@GetMapping
("/findByUsername")
public Member findByUsername(@RequestParam(name = "username") String username){
  return memberservice.findByUsername(username);
}


@GetMapping
("/numberExists")
public boolean numberExists(@RequestParam(name = "number") String number){
  return memberservice.numberExists(number);
}


@GetMapping
("/usernameExists")
public boolean usernameExists(@RequestParam(name = "username") String username){
  return memberservice.usernameExists(username);
}


@GetMapping
("/find")
public Member find(@RequestParam(name = "parent") Member parent,@RequestParam(name = "index") Integer index,@RequestParam(name = "membeRank") MemberRank membeRank){
  return memberservice.find(parent,index,membeRank);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return memberservice.findAll(Object);
}


@PutMapping
("/update")
public void update(@RequestParam(name = "member") Member member,@RequestParam(name = "modifyPoint") Integer modifyPoint,@RequestParam(name = "modifyBalance") BigDecimal modifyBalance,@RequestParam(name = "depositMemo") String depositMemo,@RequestParam(name = "operator") Admin operator){
memberservice.update(member,modifyPoint,modifyBalance,depositMemo,operator);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return memberservice.delete(Object);
}


@GetMapping
("/getCurrentUsername")
public String getCurrentUsername(){
  return memberservice.getCurrentUsername();
}


@GetMapping
("/equals")
public Object equals(@RequestParam(name = "Object") Object Object){
  return memberservice.equals(Object);
}


@GetMapping
("/count")
public long count(@RequestParam(name = "memberRank") MemberRank memberRank,@RequestParam(name = "beginDate") Date beginDate,@RequestParam(name = "endDate") Date endDate){
  return memberservice.count(memberRank,beginDate,endDate);
}


@GetMapping
("/countBalance")
public BigDecimal countBalance(@RequestParam(name = "type") Integer type,@RequestParam(name = "beginDate") Date beginDate,@RequestParam(name = "endDate") Date endDate){
  return memberservice.countBalance(type,beginDate,endDate);
}


}