package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BonudsServiceController {

 private BonudsService bonudsservice;


@GetMapping
("/count")
public BigDecimal count(@RequestParam(name = "member") Member member,@RequestParam(name = "type") Type type,@RequestParam(name = "beginDate") Date beginDate,@RequestParam(name = "endDate") Date endDate){
  return bonudsservice.count(member,type,beginDate,endDate);
}


@GetMapping
("/findList")
public List<Bonuds> findList(@RequestParam(name = "member") Member member,@RequestParam(name = "type") Type type,@RequestParam(name = "beginDate") Date beginDate,@RequestParam(name = "endDate") Date endDate){
  return bonudsservice.findList(member,type,beginDate,endDate);
}


@GetMapping
("/findToday")
public List<Bonuds> findToday(@RequestParam(name = "type") Type type,@RequestParam(name = "member") Member member){
  return bonudsservice.findToday(type,member);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return bonudsservice.save(Object);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return bonudsservice.findAll(Object);
}


@GetMapping
("/delete")
public Object delete(@RequestParam(name = "Object") Object Object){
  return bonudsservice.delete(Object);
}


@GetMapping
("/countBalance")
public BigDecimal countBalance(@RequestParam(name = "type") Type type,@RequestParam(name = "beginDate") Date beginDate,@RequestParam(name = "endDate") Date endDate){
  return bonudsservice.countBalance(type,beginDate,endDate);
}


}