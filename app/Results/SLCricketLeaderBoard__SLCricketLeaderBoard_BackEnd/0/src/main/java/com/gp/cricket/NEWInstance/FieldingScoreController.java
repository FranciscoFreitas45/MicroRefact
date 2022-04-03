package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class FieldingScoreController {

 private FieldingScoreRepository fieldingscorerepository;


@PutMapping
("/updateFieldingPoints/{id}")
public void updateFieldingPoints(@PathVariable(name = "id") Integer id,@RequestParam(name = "fieldingPoints2") Double fieldingPoints2){
 fieldingscorerepository.updateFieldingPoints(id,fieldingPoints2);
}


@PutMapping
("/setFieldingId/{id}")
public void setFieldingId(@PathVariable(name = "id") Integer id,@RequestParam(name = "fieldingId") Integer fieldingId){
 fieldingscorerepository.setFieldingId(id,fieldingId);
}


@PutMapping
("/setPlayerId/{id}")
public void setPlayerId(@PathVariable(name = "id") Integer id,@RequestParam(name = "playerId") Player playerId){
 fieldingscorerepository.setPlayerId(id,playerId);
}


@PutMapping
("/setMatchTypeId/{id}")
public void setMatchTypeId(@PathVariable(name = "id") Integer id,@RequestParam(name = "matchTypeId") MatchType matchTypeId){
 fieldingscorerepository.setMatchTypeId(id,matchTypeId);
}


@PutMapping
("/setNumberOfCatch/{id}")
public void setNumberOfCatch(@PathVariable(name = "id") Integer id,@RequestParam(name = "numberOfCatch") Integer numberOfCatch){
 fieldingscorerepository.setNumberOfCatch(id,numberOfCatch);
}


@PutMapping
("/setFieldingPoints/{id}")
public void setFieldingPoints(@PathVariable(name = "id") Integer id,@RequestParam(name = "fieldingPoints") Double fieldingPoints){
 fieldingscorerepository.setFieldingPoints(id,fieldingPoints);
}


}