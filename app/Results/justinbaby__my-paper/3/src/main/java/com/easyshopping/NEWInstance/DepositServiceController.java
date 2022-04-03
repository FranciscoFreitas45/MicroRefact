package com.easyshopping.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class DepositServiceController {

 private DepositService depositservice;


@GetMapping
("/findPage")
public Page<Deposit> findPage(@RequestParam(name = "member") Member member,@RequestParam(name = "pageable") Pageable pageable){
  return depositservice.findPage(member,pageable);
}


}