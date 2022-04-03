package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BatmanRecordRepositoryController {

 private BatmanRecordRepository batmanrecordrepository;


@GetMapping
("/findByPlayerIdANDMatchType")
public List<BatmanRecord> findByPlayerIdANDMatchType(@RequestParam(name = "playerId") Integer playerId,@RequestParam(name = "matchType") Integer matchType){
  return batmanrecordrepository.findByPlayerIdANDMatchType(playerId,matchType);
}


@GetMapping
("/findByUserId")
public List<BatmanRecord> findByUserId(@RequestParam(name = "userId") Integer userId){
  return batmanrecordrepository.findByUserId(userId);
}


}