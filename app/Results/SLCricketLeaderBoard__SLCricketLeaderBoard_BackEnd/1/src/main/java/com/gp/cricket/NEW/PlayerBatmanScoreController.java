package com.gp.cricket.NEW;
 import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.entity.Player;
@RestController
@CrossOrigin
public class PlayerBatmanScoreController {

@Autowired
 private PlayerBatmanScoreService playerbatmanscoreservice;


@PutMapping
("/BatmanScore/{id}/Player/setPlayerId")
public void setPlayerId(@PathVariable(name="id") Integer playerIdv2,@RequestBody Player playerId){
playerbatmanscoreservice.setPlayerId(playerIdv2,playerId);
}


@GetMapping
("/BatmanScore/{id}/Player/getPlayerId")
public Player getPlayerId(@PathVariable(name="id") Integer playerIdv2){
return playerbatmanscoreservice.getPlayerId(playerIdv2);
}


@GetMapping
("/BatmanScore/{id}/Player/player")
public Player player(@PathVariable(name="id") Integer playerIdv2){
return playerbatmanscoreservice.player(playerIdv2);
}


}