package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class BallerScoreController {

 private BallerScoreRepository ballerscorerepository;


@PutMapping
("/updateMatchCount/{id}")
public void updateMatchCount(@PathVariable(name = "id") Integer id,@RequestParam(name = "mathcCount") Integer mathcCount){
 ballerscorerepository.updateMatchCount(id,mathcCount);
}


@PutMapping
("/setAverageSpeed/{id}")
public void setAverageSpeed(@PathVariable(name = "id") Integer id,@RequestParam(name = "averageSpeed") Double averageSpeed){
 ballerscorerepository.setAverageSpeed(id,averageSpeed);
}


@PutMapping
("/updateOvers/{id}")
public void updateOvers(@PathVariable(name = "id") Integer id,@RequestParam(name = "overs") Double overs){
 ballerscorerepository.updateOvers(id,overs);
}


@PutMapping
("/updateWickets/{id}")
public void updateWickets(@PathVariable(name = "id") Integer id,@RequestParam(name = "wickets") Integer wickets){
 ballerscorerepository.updateWickets(id,wickets);
}


@PutMapping
("/updateWideBalls/{id}")
public void updateWideBalls(@PathVariable(name = "id") Integer id,@RequestParam(name = "wideBalls") Integer wideBalls){
 ballerscorerepository.updateWideBalls(id,wideBalls);
}


@PutMapping
("/updateNoBalls/{id}")
public void updateNoBalls(@PathVariable(name = "id") Integer id,@RequestParam(name = "noBalls") Integer noBalls){
 ballerscorerepository.updateNoBalls(id,noBalls);
}


@PutMapping
("/updateNumberOfRunsAgainst/{id}")
public void updateNumberOfRunsAgainst(@PathVariable(name = "id") Integer id,@RequestParam(name = "numberOfRunsAgainst") Integer numberOfRunsAgainst){
 ballerscorerepository.updateNumberOfRunsAgainst(id,numberOfRunsAgainst);
}


@PutMapping
("/updateHatricks/{id}")
public void updateHatricks(@PathVariable(name = "id") Integer id,@RequestParam(name = "hatricks") Integer hatricks){
 ballerscorerepository.updateHatricks(id,hatricks);
}


@PutMapping
("/updatePoints/{id}")
public void updatePoints(@PathVariable(name = "id") Integer id,@RequestParam(name = "points") Double points){
 ballerscorerepository.updatePoints(id,points);
}


@PutMapping
("/setBallerScoredId/{id}")
public void setBallerScoredId(@PathVariable(name = "id") Integer id,@RequestParam(name = "ballerScoredId") Integer ballerScoredId){
 ballerscorerepository.setBallerScoredId(id,ballerScoredId);
}


@PutMapping
("/setPlayerId/{id}")
public void setPlayerId(@PathVariable(name = "id") Integer id,@RequestParam(name = "playerId") Player playerId){
 ballerscorerepository.setPlayerId(id,playerId);
}


@PutMapping
("/setMatchTypeId/{id}")
public void setMatchTypeId(@PathVariable(name = "id") Integer id,@RequestParam(name = "matchTypeId") MatchType matchTypeId){
 ballerscorerepository.setMatchTypeId(id,matchTypeId);
}


@PutMapping
("/setMatchCount/{id}")
public void setMatchCount(@PathVariable(name = "id") Integer id,@RequestParam(name = "mathcCount") Integer mathcCount){
 ballerscorerepository.setMatchCount(id,mathcCount);
}


@PutMapping
("/setOvers/{id}")
public void setOvers(@PathVariable(name = "id") Integer id,@RequestParam(name = "overs") Double overs){
 ballerscorerepository.setOvers(id,overs);
}


@PutMapping
("/setWickets/{id}")
public void setWickets(@PathVariable(name = "id") Integer id,@RequestParam(name = "wickets") Integer wickets){
 ballerscorerepository.setWickets(id,wickets);
}


@PutMapping
("/setWideBalls/{id}")
public void setWideBalls(@PathVariable(name = "id") Integer id,@RequestParam(name = "wideBalls") Integer wideBalls){
 ballerscorerepository.setWideBalls(id,wideBalls);
}


@PutMapping
("/setNoBalls/{id}")
public void setNoBalls(@PathVariable(name = "id") Integer id,@RequestParam(name = "noBalls") Integer noBalls){
 ballerscorerepository.setNoBalls(id,noBalls);
}


@PutMapping
("/setNumberOfRunsAgainst/{id}")
public void setNumberOfRunsAgainst(@PathVariable(name = "id") Integer id,@RequestParam(name = "numberOfRunsAgainst") Integer numberOfRunsAgainst){
 ballerscorerepository.setNumberOfRunsAgainst(id,numberOfRunsAgainst);
}


@PutMapping
("/setHatricks/{id}")
public void setHatricks(@PathVariable(name = "id") Integer id,@RequestParam(name = "hatricks") Integer hatricks){
 ballerscorerepository.setHatricks(id,hatricks);
}


@PutMapping
("/setPoints/{id}")
public void setPoints(@PathVariable(name = "id") Integer id,@RequestParam(name = "points") Double points){
 ballerscorerepository.setPoints(id,points);
}


}