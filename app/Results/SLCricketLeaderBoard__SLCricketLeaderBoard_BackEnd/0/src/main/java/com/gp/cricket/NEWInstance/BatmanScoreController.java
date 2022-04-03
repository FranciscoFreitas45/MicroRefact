package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BatmanScoreController {

 private BatmanScoreRepository batmanscorerepository;


@PutMapping
("/updateMatchCount/{id}")
public void updateMatchCount(@PathVariable(name = "id") Integer id,@RequestParam(name = "matchCount") Integer matchCount){
 batmanscorerepository.updateMatchCount(id,matchCount);
}


@PutMapping
("/setStrikeRate/{id}")
public void setStrikeRate(@PathVariable(name = "id") Integer id,@RequestParam(name = "strikeRate") Double strikeRate){
 batmanscorerepository.setStrikeRate(id,strikeRate);
}


@PutMapping
("/updateFacedBalls/{id}")
public void updateFacedBalls(@PathVariable(name = "id") Integer id,@RequestParam(name = "facedBalls") Integer facedBalls){
 batmanscorerepository.updateFacedBalls(id,facedBalls);
}


@PutMapping
("/updateFour/{id}")
public void updateFour(@PathVariable(name = "id") Integer id,@RequestParam(name = "four") Integer four){
 batmanscorerepository.updateFour(id,four);
}


@PutMapping
("/updateSix/{id}")
public void updateSix(@PathVariable(name = "id") Integer id,@RequestParam(name = "six") Integer six){
 batmanscorerepository.updateSix(id,six);
}


@PutMapping
("/updateRuns/{id}")
public void updateRuns(@PathVariable(name = "id") Integer id,@RequestParam(name = "runs") Integer runs){
 batmanscorerepository.updateRuns(id,runs);
}


@PutMapping
("/updatePoints/{id}")
public void updatePoints(@PathVariable(name = "id") Integer id,@RequestParam(name = "points") Double points){
 batmanscorerepository.updatePoints(id,points);
}


@PutMapping
("/updateHalfCenturies/{id}")
public void updateHalfCenturies(@PathVariable(name = "id") Integer id,@RequestParam(name = "halfCenturies") Integer halfCenturies){
 batmanscorerepository.updateHalfCenturies(id,halfCenturies);
}


@PutMapping
("/updateCenturies/{id}")
public void updateCenturies(@PathVariable(name = "id") Integer id,@RequestParam(name = "centuries") Integer centuries){
 batmanscorerepository.updateCenturies(id,centuries);
}


@PutMapping
("/updateDoubleCenturies/{id}")
public void updateDoubleCenturies(@PathVariable(name = "id") Integer id,@RequestParam(name = "doubleCenturies") Integer doubleCenturies){
 batmanscorerepository.updateDoubleCenturies(id,doubleCenturies);
}


@PutMapping
("/updateTripleCenturies/{id}")
public void updateTripleCenturies(@PathVariable(name = "id") Integer id,@RequestParam(name = "tripleCenturies") Integer tripleCenturies){
 batmanscorerepository.updateTripleCenturies(id,tripleCenturies);
}


@PutMapping
("/updateFourbleCenturies/{id}")
public void updateFourbleCenturies(@PathVariable(name = "id") Integer id,@RequestParam(name = "fourbleCenturies") Integer fourbleCenturies){
 batmanscorerepository.updateFourbleCenturies(id,fourbleCenturies);
}


@PutMapping
("/updateFivebleCenturies/{id}")
public void updateFivebleCenturies(@PathVariable(name = "id") Integer id,@RequestParam(name = "fivebleCenturies") Integer fivebleCenturies){
 batmanscorerepository.updateFivebleCenturies(id,fivebleCenturies);
}


@PutMapping
("/updateNotOut/{id}")
public void updateNotOut(@PathVariable(name = "id") Integer id,@RequestParam(name = "notOut") Integer notOut){
 batmanscorerepository.updateNotOut(id,notOut);
}


@PutMapping
("/setBatmanScoreId/{id}")
public void setBatmanScoreId(@PathVariable(name = "id") Integer id,@RequestParam(name = "batmanScoreId") Integer batmanScoreId){
 batmanscorerepository.setBatmanScoreId(id,batmanScoreId);
}


@PutMapping
("/setPlayerId/{id}")
public void setPlayerId(@PathVariable(name = "id") Integer id,@RequestParam(name = "playerId") Player playerId){
 batmanscorerepository.setPlayerId(id,playerId);
}


@PutMapping
("/setMatchTypeId/{id}")
public void setMatchTypeId(@PathVariable(name = "id") Integer id,@RequestParam(name = "matchTypeId") MatchType matchTypeId){
 batmanscorerepository.setMatchTypeId(id,matchTypeId);
}


@PutMapping
("/setMatchCount/{id}")
public void setMatchCount(@PathVariable(name = "id") Integer id,@RequestParam(name = "matchCount") Integer matchCount){
 batmanscorerepository.setMatchCount(id,matchCount);
}


@PutMapping
("/setFacedBalls/{id}")
public void setFacedBalls(@PathVariable(name = "id") Integer id,@RequestParam(name = "facedBalls") Integer facedBalls){
 batmanscorerepository.setFacedBalls(id,facedBalls);
}


@PutMapping
("/setFour/{id}")
public void setFour(@PathVariable(name = "id") Integer id,@RequestParam(name = "four") Integer four){
 batmanscorerepository.setFour(id,four);
}


@PutMapping
("/setSix/{id}")
public void setSix(@PathVariable(name = "id") Integer id,@RequestParam(name = "six") Integer six){
 batmanscorerepository.setSix(id,six);
}


@PutMapping
("/setRuns/{id}")
public void setRuns(@PathVariable(name = "id") Integer id,@RequestParam(name = "runs") Integer runs){
 batmanscorerepository.setRuns(id,runs);
}


@PutMapping
("/setPoints/{id}")
public void setPoints(@PathVariable(name = "id") Integer id,@RequestParam(name = "points") Double points){
 batmanscorerepository.setPoints(id,points);
}


@PutMapping
("/setHalfCenturies/{id}")
public void setHalfCenturies(@PathVariable(name = "id") Integer id,@RequestParam(name = "halfCenturies") Integer halfCenturies){
 batmanscorerepository.setHalfCenturies(id,halfCenturies);
}


@PutMapping
("/setCenturies/{id}")
public void setCenturies(@PathVariable(name = "id") Integer id,@RequestParam(name = "centuries") Integer centuries){
 batmanscorerepository.setCenturies(id,centuries);
}


@PutMapping
("/setDoubleCenturies/{id}")
public void setDoubleCenturies(@PathVariable(name = "id") Integer id,@RequestParam(name = "doubleCenturies") Integer doubleCenturies){
 batmanscorerepository.setDoubleCenturies(id,doubleCenturies);
}


@PutMapping
("/setTripleCenturies/{id}")
public void setTripleCenturies(@PathVariable(name = "id") Integer id,@RequestParam(name = "tripleCenturies") Integer tripleCenturies){
 batmanscorerepository.setTripleCenturies(id,tripleCenturies);
}


@PutMapping
("/setFourbleCenturies/{id}")
public void setFourbleCenturies(@PathVariable(name = "id") Integer id,@RequestParam(name = "fourbleCenturies") Integer fourbleCenturies){
 batmanscorerepository.setFourbleCenturies(id,fourbleCenturies);
}


@PutMapping
("/setFivebleCenturies/{id}")
public void setFivebleCenturies(@PathVariable(name = "id") Integer id,@RequestParam(name = "fivebleCenturies") Integer fivebleCenturies){
 batmanscorerepository.setFivebleCenturies(id,fivebleCenturies);
}


@PutMapping
("/setNotOut/{id}")
public void setNotOut(@PathVariable(name = "id") Integer id,@RequestParam(name = "notOut") Integer notOut){
 batmanscorerepository.setNotOut(id,notOut);
}


}