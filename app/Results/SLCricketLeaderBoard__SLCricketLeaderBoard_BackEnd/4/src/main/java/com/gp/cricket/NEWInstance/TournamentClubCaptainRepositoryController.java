package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TournamentClubCaptainRepositoryController {

 private TournamentClubCaptainRepository tournamentclubcaptainrepository;


@GetMapping
("/findByTournamentClubId")
public TournamentClubCaptain findByTournamentClubId(@RequestParam(name = "tournamentClub") TournamentClub tournamentClub){
  return tournamentclubcaptainrepository.findByTournamentClubId(tournamentClub);
}


}