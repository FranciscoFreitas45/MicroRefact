package com.gp.cricket.Request;
import com.gp.cricket.DTO.Player;
public interface PlayerRequest {

   public void setPlayerId(Player playerId,Integer playerIdv2);
   public Player getPlayerId(Integer playerIdv2);
}