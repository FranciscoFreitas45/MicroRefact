package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FieldingScoreServiceController {

 private FieldingScoreService fieldingscoreservice;


@GetMapping
("/getRecordByPlayerIdMatchType")
public FieldingScore getRecordByPlayerIdMatchType(@RequestParam(name = "playerId") Integer playerId,@RequestParam(name = "matchTypeString") String matchTypeString){
  return fieldingscoreservice.getRecordByPlayerIdMatchType(playerId,matchTypeString);
}


@GetMapping
("/saveFieldingRecord")
public FieldingScore saveFieldingRecord(@RequestParam(name = "record") FieldingScore record){
  return fieldingscoreservice.saveFieldingRecord(record);
}


}