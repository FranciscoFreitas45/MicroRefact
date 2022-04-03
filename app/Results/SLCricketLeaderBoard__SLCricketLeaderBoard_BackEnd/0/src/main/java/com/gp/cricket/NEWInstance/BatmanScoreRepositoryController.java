package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BatmanScoreRepositoryController {

 private BatmanScoreRepository batmanscorerepository;


@GetMapping
("/getRecordByPlayerIdMatchType")
public BatmanScore getRecordByPlayerIdMatchType(@RequestParam(name = "playerId") Integer playerId,@RequestParam(name = "matchType") String matchType){
  return batmanscorerepository.getRecordByPlayerIdMatchType(playerId,matchType);
}


@GetMapping
("/save")
public Object save(@RequestParam(name = "Object") Object Object){
  return batmanscorerepository.save(Object);
}


}