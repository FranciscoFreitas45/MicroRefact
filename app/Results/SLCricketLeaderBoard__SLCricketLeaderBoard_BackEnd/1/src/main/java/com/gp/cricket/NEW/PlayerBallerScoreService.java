package com.gp.cricket.NEW;
 import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.repository.PlayerRepository;
import com.gp.cricket.entity.Player;
@Service
public class PlayerBallerScoreService {

@Autowired
 private PlayerRepository playerrepository;


public void setPlayerId(Integer playerIdv2,Player playerId){
playerrepository.setPlayerId(playerIdv2,playerId);
}


public Player getPlayerId(Integer playerIdv2){
return playerrepository.getPlayerId(playerIdv2);
}


}