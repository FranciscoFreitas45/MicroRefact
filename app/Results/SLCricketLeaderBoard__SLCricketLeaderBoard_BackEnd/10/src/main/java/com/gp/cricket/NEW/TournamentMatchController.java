package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.Tournament;
@RestController
@CrossOrigin
public class TournamentMatchController {

@Autowired
 private TournamentMatchService tournamentmatchservice;


@GetMapping
("/Match/{id}/Tournament/getTournamentId")
public Tournament getTournamentId(@PathVariable(name="id") Integer tournamentIdv2){
return tournamentmatchservice.getTournamentId(tournamentIdv2);
}


@PutMapping
("/Match/{id}/Tournament/setTournamentId")
public void setTournamentId(@PathVariable(name="id") Integer tournamentIdv2,@RequestBody Tournament tournamentId){
tournamentmatchservice.setTournamentId(tournamentIdv2,tournamentId);
}


}