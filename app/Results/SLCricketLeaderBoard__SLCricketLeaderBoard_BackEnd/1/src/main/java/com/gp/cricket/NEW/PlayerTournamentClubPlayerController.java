package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.Player;
@RestController
@CrossOrigin
public class PlayerTournamentClubPlayerController {

@Autowired
 private PlayerTournamentClubPlayerService playertournamentclubplayerservice;


@PutMapping
("/TournamentClubPlayer/{id}/Player/setPlayerId")
public void setPlayerId(@PathVariable(name="id") Integer playerIdv2,@RequestBody Player playerId){
playertournamentclubplayerservice.setPlayerId(playerIdv2,playerId);
}


@GetMapping
("/TournamentClubPlayer/{id}/Player/getPlayerId")
public Player getPlayerId(@PathVariable(name="id") Integer playerIdv2){
return playertournamentclubplayerservice.getPlayerId(playerIdv2);
}


}