package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class SelectedPlayerRepositoryController {

 private SelectedPlayerRepository selectedplayerrepository;


@GetMapping
("/selectedPlayersForMatch")
public List<Player> selectedPlayersForMatch(@RequestParam(name = "matchId") Integer matchId,@RequestParam(name = "clubId") Integer clubId){
  return selectedplayerrepository.selectedPlayersForMatch(matchId,clubId);
}


@GetMapping
("/findById")
public Object findById(@RequestParam(name = "Object") Object Object){
  return selectedplayerrepository.findById(Object);
}


}