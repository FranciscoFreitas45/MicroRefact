package com.lingxiang2014.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class StaticBonudsRankServiceController {

 private StaticBonudsRankService staticbonudsrankservice;


@GetMapping
("/findDefault")
public StaticBonudsRank findDefault(){
  return staticbonudsrankservice.findDefault();
}


@GetMapping
("/findLTEQ")
public List<StaticBonudsRank> findLTEQ(@RequestParam(name = "myPeople") Integer myPeople){
  return staticbonudsrankservice.findLTEQ(myPeople);
}


}