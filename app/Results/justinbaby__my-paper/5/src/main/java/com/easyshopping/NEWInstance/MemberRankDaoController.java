package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MemberRankDaoController {

 private MemberRankDao memberrankdao;


@GetMapping
("/findByAmount")
public MemberRank findByAmount(@RequestParam(name = "amount") BigDecimal amount){
  return memberrankdao.findByAmount(amount);
}


}