package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TournamentServiceController {

 private TournamentService tournamentservice;


@GetMapping
("/getTournamentsByDateOrder")
public List<Tournament> getTournamentsByDateOrder(){
  return tournamentservice.getTournamentsByDateOrder();
}


@GetMapping
("/getTournamentsHistory")
public List<Tournament> getTournamentsHistory(){
  return tournamentservice.getTournamentsHistory();
}


}