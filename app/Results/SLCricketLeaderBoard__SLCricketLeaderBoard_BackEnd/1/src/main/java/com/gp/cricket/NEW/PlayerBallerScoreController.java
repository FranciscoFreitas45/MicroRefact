package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.Player;
@RestController
@CrossOrigin
public class PlayerBallerScoreController {

@Autowired
 private PlayerBallerScoreService playerballerscoreservice;


@PutMapping
("/BallerScore/{id}/Player/setPlayerId")
public void setPlayerId(@PathVariable(name="id") Integer playerIdv2,@RequestBody Player playerId){
playerballerscoreservice.setPlayerId(playerIdv2,playerId);
}


@GetMapping
("/BallerScore/{id}/Player/getPlayerId")
public Player getPlayerId(@PathVariable(name="id") Integer playerIdv2){
return playerballerscoreservice.getPlayerId(playerIdv2);
}


}