package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BallerRecordRepositoryController {

 private BallerRecordRepository ballerrecordrepository;


@GetMapping
("/findByPlayerIdANDMatchId")
public BallerRecord findByPlayerIdANDMatchId(@RequestParam(name = "playerId") Integer playerId,@RequestParam(name = "matchId") Match matchId){
  return ballerrecordrepository.findByPlayerIdANDMatchId(playerId,matchId);
}


@GetMapping
("/findByPlayerIdANDMatchType")
public List<BallerRecord> findByPlayerIdANDMatchType(@RequestParam(name = "playerId") Integer playerId,@RequestParam(name = "matchType") Integer matchType){
  return ballerrecordrepository.findByPlayerIdANDMatchType(playerId,matchType);
}


@GetMapping
("/findByUserId")
public List<BallerRecord> findByUserId(@RequestParam(name = "userId") Integer userId){
  return ballerrecordrepository.findByUserId(userId);
}


}