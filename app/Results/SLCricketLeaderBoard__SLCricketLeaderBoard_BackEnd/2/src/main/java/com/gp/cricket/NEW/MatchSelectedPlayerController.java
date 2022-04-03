package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.Match;
@RestController
@CrossOrigin
public class MatchSelectedPlayerController {

@Autowired
 private MatchSelectedPlayerService matchselectedplayerservice;


@GetMapping
("/SelectedPlayer/{id}/Match/getMatchId")
public Match getMatchId(@PathVariable(name="id") Integer matchIdv2){
return matchselectedplayerservice.getMatchId(matchIdv2);
}


@PutMapping
("/SelectedPlayer/{id}/Match/setMatchId")
public void setMatchId(@PathVariable(name="id") Integer matchIdv2,@RequestBody Match matchId){
matchselectedplayerservice.setMatchId(matchIdv2,matchId);
}


}