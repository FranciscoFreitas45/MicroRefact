package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MemberController {

 private Member member;

 private Member member;


@PutMapping
("/setBalance2")
public void setBalance2(@RequestParam(name = "balance2") BigDecimal balance2){
member.setBalance2(balance2);
}


@PutMapping
("/setBalance1")
public void setBalance1(@RequestParam(name = "balance1") BigDecimal balance1){
member.setBalance1(balance1);
}


@PutMapping
("/setBalance")
public void setBalance(@RequestParam(name = "balance") BigDecimal balance){
member.setBalance(balance);
}


@PutMapping
("/setBalance3")
public void setBalance3(@RequestParam(name = "balance3") BigDecimal balance3){
member.setBalance3(balance3);
}


@PutMapping
("/setLeftChildren")
public void setLeftChildren(@RequestParam(name = "leftChildren") Member leftChildren){
member.setLeftChildren(leftChildren);
}


@PutMapping
("/setRightChildren")
public void setRightChildren(@RequestParam(name = "rightChildren") Member rightChildren){
member.setRightChildren(rightChildren);
}


@PutMapping
("/setMidChildren")
public void setMidChildren(@RequestParam(name = "midChildren") Member midChildren){
member.setMidChildren(midChildren);
}


@PutMapping
("/setParent")
public void setParent(@RequestParam(name = "parent") Member parent){
member.setParent(parent);
}


@PutMapping
("/setStaticDevidedBonuds")
public void setStaticDevidedBonuds(@RequestParam(name = "staticDevidedBonuds") BigDecimal staticDevidedBonuds){
member.setStaticDevidedBonuds(staticDevidedBonuds);
}


@PutMapping
("/setTodayResults")
public void setTodayResults(@RequestParam(name = "todayResults") BigDecimal todayResults){
member.setTodayResults(todayResults);
}


}