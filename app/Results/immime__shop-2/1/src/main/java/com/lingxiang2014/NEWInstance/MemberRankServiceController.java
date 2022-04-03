package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MemberRankServiceController {

 private MemberRankService memberrankservice;


@GetMapping
("/findDefault")
public MemberRank findDefault(){
  return memberrankservice.findDefault();
}


}