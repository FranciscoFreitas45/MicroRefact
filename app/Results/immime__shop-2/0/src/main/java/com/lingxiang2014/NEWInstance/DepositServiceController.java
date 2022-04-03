package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DepositServiceController {

 private DepositService depositservice;


@GetMapping
("/count")
public BigDecimal count(@RequestParam(name = "member") Member member,@RequestParam(name = "type") Type type,@RequestParam(name = "beginDate") Date beginDate,@RequestParam(name = "endDate") Date endDate){
  return depositservice.count(member,type,beginDate,endDate);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return depositservice.findAll(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return depositservice.delete(Object);
}


@GetMapping
("/countBalance")
public BigDecimal countBalance(@RequestParam(name = "type") Type type,@RequestParam(name = "beginDate") Date beginDate,@RequestParam(name = "endDate") Date endDate){
  return depositservice.countBalance(type,beginDate,endDate);
}


}