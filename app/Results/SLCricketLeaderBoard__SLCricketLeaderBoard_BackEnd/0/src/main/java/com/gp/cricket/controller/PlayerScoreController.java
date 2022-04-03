package com.gp.cricket.controller;
 import java.util.List;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.mapobject.PlayerMatchRecord;
import com.gp.cricket.mapobject.PlayerRate;
import com.gp.cricket.service.PlayerScoreService;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class PlayerScoreController {

@Autowired
 private PlayerScoreService playerScoreService;


@GetMapping("playerscore/{clubId}/{playerType}/{order}")
public ResponseEntity<List<PlayerRate>> getPlayerRateList(Integer clubId,Integer playerType,Integer order){
    return ResponseEntity.ok(playerScoreService.getPlayerRateList(clubId, playerType, order));
}


@GetMapping("playerscore/playerrecord/{playerId}/{playerType}/{matchType}")
public ResponseEntity<List<PlayerMatchRecord>> getPlayerMatchRecord(Integer playerId,Integer playerType,Integer matchType){
    return ResponseEntity.ok(playerScoreService.getPlayerMatchRecord(playerId, playerType, matchType));
}


}