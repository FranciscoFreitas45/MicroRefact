package com.gp.cricket.Request;
import com.gp.cricket.DTO.SelectedPlayer;
public interface SelectedPlayerRequest {

   public void setSelectedPlayerId(SelectedPlayer selectedPlayerId,Integer selectedPlayerIdv2);
   public SelectedPlayer getSelectedPlayerId(Integer selectedPlayerIdv2);
}