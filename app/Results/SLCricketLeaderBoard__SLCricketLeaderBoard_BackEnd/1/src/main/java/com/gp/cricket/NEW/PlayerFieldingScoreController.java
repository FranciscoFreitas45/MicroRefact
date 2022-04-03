package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.Player;
@RestController
@CrossOrigin
public class PlayerFieldingScoreController {

@Autowired
 private PlayerFieldingScoreService playerfieldingscoreservice;


@PutMapping
("/FieldingScore/{id}/Player/setPlayerId")
public void setPlayerId(@PathVariable(name="id") Integer playerIdv2,@RequestBody Player playerId){
playerfieldingscoreservice.setPlayerId(playerIdv2,playerId);
}


@GetMapping
("/FieldingScore/{id}/Player/getPlayerId")
public Player getPlayerId(@PathVariable(name="id") Integer playerIdv2){
return playerfieldingscoreservice.getPlayerId(playerIdv2);
}


}