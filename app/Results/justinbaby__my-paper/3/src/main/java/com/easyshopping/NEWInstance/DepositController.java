package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DepositController {

 private Deposit deposit;

 private Deposit deposit;


@PutMapping
("/setCredit")
public void setCredit(@RequestParam(name = "credit") BigDecimal credit){
deposit.setCredit(credit);
}


@PutMapping
("/setDebit")
public void setDebit(@RequestParam(name = "debit") BigDecimal debit){
deposit.setDebit(debit);
}


@PutMapping
("/setBalance")
public void setBalance(@RequestParam(name = "balance") BigDecimal balance){
deposit.setBalance(balance);
}


@PutMapping
("/setOperator")
public void setOperator(@RequestParam(name = "operator") String operator){
deposit.setOperator(operator);
}


@PutMapping
("/setMember")
public void setMember(@RequestParam(name = "member") Member member){
deposit.setMember(member);
}


@PutMapping
("/setOrder")
public void setOrder(@RequestParam(name = "order") Order order){
deposit.setOrder(order);
}


@PutMapping
("/setMemo")
public void setMemo(@RequestParam(name = "memo") String memo){
deposit.setMemo(memo);
}


}