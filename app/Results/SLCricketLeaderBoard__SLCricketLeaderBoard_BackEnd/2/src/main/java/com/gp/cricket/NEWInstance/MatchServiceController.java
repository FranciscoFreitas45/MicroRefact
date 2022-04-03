package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class MatchServiceController {

 private MatchService matchservice;


@GetMapping
("/findMatchesByTournamentIdForCalender")
public List<Match> findMatchesByTournamentIdForCalender(@RequestParam(name = "tournamentId") Integer tournamentId){
  return matchservice.findMatchesByTournamentIdForCalender(tournamentId);
}


}