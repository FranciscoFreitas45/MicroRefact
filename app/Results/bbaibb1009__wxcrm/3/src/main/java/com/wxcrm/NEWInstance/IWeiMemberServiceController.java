package com.wxcrm.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class IWeiMemberServiceController {

 private IWeiMemberService iweimemberservice;


@GetMapping
("/queryWeiMember")
public Page queryWeiMember(@RequestParam(name = "member") LzWeiMember member){
  return iweimemberservice.queryWeiMember(member);
}


@GetMapping
("/checkOpenIdExsit")
public boolean checkOpenIdExsit(@RequestParam(name = "openId") String openId,@RequestParam(name = "wecId") Integer wecId){
  return iweimemberservice.checkOpenIdExsit(openId,wecId);
}


@PutMapping
("/addWeiMember")
public void addWeiMember(@RequestParam(name = "member") LzWeiMember member){
iweimemberservice.addWeiMember(member);
}


@PutMapping
("/delWeiMember")
public void delWeiMember(@RequestParam(name = "wmbIds") String[] wmbIds){
iweimemberservice.delWeiMember(wmbIds);
}


}