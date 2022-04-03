package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.Tournament;
@RestController
@CrossOrigin
public class TournamentTournamentClubController {

@Autowired
 private TournamentTournamentClubService tournamenttournamentclubservice;


@PutMapping
("/TournamentClub/{id}/Tournament/setTournamentId")
public void setTournamentId(@PathVariable(name="id") Integer tournamentIdv2,@RequestBody Tournament tournamentId){
tournamenttournamentclubservice.setTournamentId(tournamentIdv2,tournamentId);
}


@GetMapping
("/TournamentClub/{id}/Tournament/getTournamentId")
public Tournament getTournamentId(@PathVariable(name="id") Integer tournamentIdv2){
return tournamenttournamentclubservice.getTournamentId(tournamentIdv2);
}


}