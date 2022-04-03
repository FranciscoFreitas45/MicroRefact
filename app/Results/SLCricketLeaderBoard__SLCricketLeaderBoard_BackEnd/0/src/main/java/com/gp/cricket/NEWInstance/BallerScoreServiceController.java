package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BallerScoreServiceController {

 private BallerScoreService ballerscoreservice;


@GetMapping
("/getRecordByPlayerIdMatchType")
public BallerScore getRecordByPlayerIdMatchType(@RequestParam(name = "playerId") Integer playerId,@RequestParam(name = "matchTypeString") String matchTypeString){
  return ballerscoreservice.getRecordByPlayerIdMatchType(playerId,matchTypeString);
}


@GetMapping
("/saveRecord")
public BallerScore saveRecord(@RequestParam(name = "ballerScoreRecord") BallerScore ballerScoreRecord){
  return ballerscoreservice.saveRecord(ballerScoreRecord);
}


}