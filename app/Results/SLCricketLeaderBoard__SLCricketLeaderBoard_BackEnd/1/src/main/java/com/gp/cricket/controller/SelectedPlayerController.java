package com.gp.cricket.controller;
 import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.gp.cricket.entity.SelectedPlayer;
import com.gp.cricket.service.SelectedPlayerService;
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class SelectedPlayerController {

@Autowired
 private SelectedPlayerService selectedPlayerService;


@GetMapping("/match/selectedPlayersUpdated/{matchId}/{clubId}")
public List<SelectedPlayer> getMatchePlayersUpdated(Integer matchId,Integer clubId){
    return selectedPlayerService.getSelectedPlayersUpdated(matchId, clubId);
}


@GetMapping("/match/selectedPlayersNotUpdated/{matchId}/{clubId}")
public List<SelectedPlayer> getMatchePlayersNotUpdated(Integer matchId,Integer clubId){
    return selectedPlayerService.getSelectedPlayersNotUpdated(matchId, clubId);
}


@GetMapping("/selectedPlayer/{id}")
public SelectedPlayer getMatchePlayers(Integer id){
    return selectedPlayerService.getSelectedPlayerById(id);
}


}