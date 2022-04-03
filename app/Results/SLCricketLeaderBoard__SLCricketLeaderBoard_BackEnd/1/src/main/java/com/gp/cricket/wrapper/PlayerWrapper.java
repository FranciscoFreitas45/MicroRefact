package com.gp.cricket.wrapper;
 import java.util.List;
import com.gp.cricket.entity.Player;
public class PlayerWrapper {

 private  List<Player> playerList;

public PlayerWrapper() {
}public PlayerWrapper(List<Player> playerList) {
    super();
    this.playerList = playerList;
}
@Override
public String toString(){
    return "PlayerWrapper [playerList=" + playerList + "]";
}


public List<Player> getPlayerList(){
    return playerList;
}


public void setPlayerList(List<Player> playerList){
    this.playerList = playerList;
}


}