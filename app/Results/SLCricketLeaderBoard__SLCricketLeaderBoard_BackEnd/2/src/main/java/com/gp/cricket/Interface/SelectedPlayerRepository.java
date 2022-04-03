package com.gp.cricket.Interface;
public interface SelectedPlayerRepository {

   public List<Player> selectedPlayersForMatch(Integer matchId,Integer clubId);
}