package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BonudsController {

 private Bonuds bonuds;

 private Bonuds bonuds;


@PutMapping
("/setBalance")
public void setBalance(@RequestParam(name = "balance") BigDecimal balance){
bonuds.setBalance(balance);
}


@PutMapping
("/setMember")
public void setMember(@RequestParam(name = "member") Member member){
bonuds.setMember(member);
}


@PutMapping
("/setMemo")
public void setMemo(@RequestParam(name = "memo") String memo){
bonuds.setMemo(memo);
}


@PutMapping
("/setOperator")
public void setOperator(@RequestParam(name = "operator") String operator){
bonuds.setOperator(operator);
}


@PutMapping
("/setType")
public void setType(@RequestParam(name = "type") Type type){
bonuds.setType(type);
}


}