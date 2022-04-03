package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class PlayerRepositoryController {

 private PlayerRepository playerrepository;


@GetMapping
("/findRemainingBatmenPlayers")
public List<Player> findRemainingBatmenPlayers(@RequestParam(name = "clubId") Club clubId,@RequestParam(name = "playerType") Integer playerType,@RequestParam(name = "matchTypeId") Integer matchTypeId){
  return playerrepository.findRemainingBatmenPlayers(clubId,playerType,matchTypeId);
}


@GetMapping
("/findRemainingBallerPlayers")
public List<Player> findRemainingBallerPlayers(@RequestParam(name = "club") Club club,@RequestParam(name = "playerType") Integer playerType,@RequestParam(name = "matchTypeId") Integer matchTypeId){
  return playerrepository.findRemainingBallerPlayers(club,playerType,matchTypeId);
}


@GetMapping
("/findPlayerById")
public Player findPlayerById(@RequestParam(name = "playerId") Integer playerId){
  return playerrepository.findPlayerById(playerId);
}


@GetMapping
("/existsById")
public Object existsById(@RequestParam(name = "Object") Object Object){
  return playerrepository.existsById(Object);
}


@GetMapping
("/getNumofPlayerinClub")
public Integer getNumofPlayerinClub(@RequestParam(name = "club") Club club){
  return playerrepository.getNumofPlayerinClub(club);
}


@GetMapping
("/findPlayerByClubId")
public List<String> findPlayerByClubId(@RequestParam(name = "clubId") Club clubId){
  return playerrepository.findPlayerByClubId(clubId);
}


@GetMapping
("/findAll")
public Object findAll(@RequestParam(name = "Object") Object Object){
  return playerrepository.findAll(Object);
}


}