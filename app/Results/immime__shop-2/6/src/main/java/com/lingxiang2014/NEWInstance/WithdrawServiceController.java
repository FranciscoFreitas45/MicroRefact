package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class WithdrawServiceController {

 private WithdrawService withdrawservice;


@GetMapping
("/count")
public BigDecimal count(@RequestParam(name = "member") Member member,@RequestParam(name = "success") Status success,@RequestParam(name = "beginDate") Date beginDate,@RequestParam(name = "endDate") Date endDate){
  return withdrawservice.count(member,success,beginDate,endDate);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return withdrawservice.findAll(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return withdrawservice.delete(Object);
}


@GetMapping
("/countBalance")
public BigDecimal countBalance(@RequestParam(name = "status") Status status,@RequestParam(name = "beginDate") Date beginDate,@RequestParam(name = "endDate") Date endDate){
  return withdrawservice.countBalance(status,beginDate,endDate);
}


}