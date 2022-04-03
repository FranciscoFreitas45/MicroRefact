package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MemberController {

 private Member member;

 private Member member;


@PutMapping
("/setBalance")
public void setBalance(@RequestParam(name = "balance") BigDecimal balance){
member.setBalance(balance);
}


@PutMapping
("/setPoint")
public void setPoint(@RequestParam(name = "point") Long point){
member.setPoint(point);
}


@PutMapping
("/setAmount")
public void setAmount(@RequestParam(name = "amount") BigDecimal amount){
member.setAmount(amount);
}


@PutMapping
("/setMemberRank")
public void setMemberRank(@RequestParam(name = "memberRank") MemberRank memberRank){
member.setMemberRank(memberRank);
}


@PutMapping
("/setCart")
public void setCart(@RequestParam(name = "cart") Cart cart){
member.setCart(cart);
}


}