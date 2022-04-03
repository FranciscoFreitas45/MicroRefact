package com.gp.cricket.DTO;
 import java.util.List;
import com.gp.cricket.entity.Player;
public class PlayerWrapper {

 private  List<Player> playerList;

public PlayerWrapper() {
}public PlayerWrapper(List<Player> playerList) {
    super();
    this.playerList = playerList;
}
public List<Player> getPlayerList(){
    return playerList;
}


}