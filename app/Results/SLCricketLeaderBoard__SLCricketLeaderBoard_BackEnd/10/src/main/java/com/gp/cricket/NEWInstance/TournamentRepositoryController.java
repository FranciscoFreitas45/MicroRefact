package com.gp.cricket.NEWInstance;
 import org.springframework.web.bind.annotation.*;
@RestController
@CrossOrigin
public class TournamentRepositoryController {

 private TournamentRepository tournamentrepository;


@GetMapping
("/existsById")
public Object existsById(@RequestParam(name = "Object") Object Object){
  return tournamentrepository.existsById(Object);
}


@GetMapping
("/findByTournamentId")
public Tournament findByTournamentId(@RequestParam(name = "tournamentId") Integer tournamentId){
  return tournamentrepository.findByTournamentId(tournamentId);
}


@GetMapping
("/findOnGoingTournament")
public List<Tournament> findOnGoingTournament(@RequestParam(name = "date") Date date){
  return tournamentrepository.findOnGoingTournament(date);
}


@GetMapping
("/findUpcomingTournament")
public List<Tournament> findUpcomingTournament(@RequestParam(name = "date") Date date){
  return tournamentrepository.findUpcomingTournament(date);
}


@GetMapping
("/findPastTournament")
public List<Tournament> findPastTournament(@RequestParam(name = "date") Date date){
  return tournamentrepository.findPastTournament(date);
}


}