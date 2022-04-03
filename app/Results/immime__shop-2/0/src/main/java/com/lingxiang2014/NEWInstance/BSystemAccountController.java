package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BSystemAccountController {

 private BSystemAccount bsystemaccount;

 private BSystemAccount bsystemaccount;


@PutMapping
("/setNumber")
public void setNumber(@RequestParam(name = "number") String number){
bsystemaccount.setNumber(number);
}


@PutMapping
("/setMember")
public void setMember(@RequestParam(name = "member") Member member){
bsystemaccount.setMember(member);
}


@PutMapping
("/setTop")
public void setTop(@RequestParam(name = "top") BSystemAccount top){
bsystemaccount.setTop(top);
}


@PutMapping
("/setRightChildren")
public void setRightChildren(@RequestParam(name = "rightChildren") BSystemAccount rightChildren){
bsystemaccount.setRightChildren(rightChildren);
}


@PutMapping
("/setIsLeaf")
public void setIsLeaf(@RequestParam(name = "isLeaf") Boolean isLeaf){
bsystemaccount.setIsLeaf(isLeaf);
}


@PutMapping
("/setLeftChildren")
public void setLeftChildren(@RequestParam(name = "leftChildren") BSystemAccount leftChildren){
bsystemaccount.setLeftChildren(leftChildren);
}


}