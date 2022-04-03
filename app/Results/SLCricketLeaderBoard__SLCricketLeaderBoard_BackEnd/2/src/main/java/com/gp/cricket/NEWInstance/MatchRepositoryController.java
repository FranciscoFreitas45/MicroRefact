package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MatchRepositoryController {

 private MatchRepository matchrepository;


@GetMapping
("/findBytournamentId")
public List<Match> findBytournamentId(@RequestParam(name = "tournamentId") Integer tournamentId){
  return matchrepository.findBytournamentId(tournamentId);
}


}