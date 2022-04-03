package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TournamentClubRepositoryController {

 private TournamentClubRepository tournamentclubrepository;


@GetMapping
("/findIdByTournamentAndClub")
public Integer findIdByTournamentAndClub(@RequestParam(name = "tournamentId") Integer tournamentId,@RequestParam(name = "clubId") Integer clubId){
  return tournamentclubrepository.findIdByTournamentAndClub(tournamentId,clubId);
}


@GetMapping
("/findByClubIdAndTournamentId")
public TournamentClub findByClubIdAndTournamentId(@RequestParam(name = "clubId") Club clubId,@RequestParam(name = "tournamentId") Tournament tournamentId){
  return tournamentclubrepository.findByClubIdAndTournamentId(clubId,tournamentId);
}


}