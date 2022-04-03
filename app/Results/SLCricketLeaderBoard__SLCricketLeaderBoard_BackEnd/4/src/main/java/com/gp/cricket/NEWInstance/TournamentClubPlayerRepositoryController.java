package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TournamentClubPlayerRepositoryController {

 private TournamentClubPlayerRepository tournamentclubplayerrepository;


@GetMapping
("/findPlayerTournamentStatus")
public List<TournamentClubPlayer> findPlayerTournamentStatus(@RequestParam(name = "playerId") Player playerId){
  return tournamentclubplayerrepository.findPlayerTournamentStatus(playerId);
}


@GetMapping
("/findPlayersForMatch")
public List<Player> findPlayersForMatch(@RequestParam(name = "tournamentClubId") Integer tournamentClubId){
  return tournamentclubplayerrepository.findPlayersForMatch(tournamentClubId);
}


}