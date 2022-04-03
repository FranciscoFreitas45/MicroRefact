package com.gp.cricket.Interface;
public interface PlayerRepository {

   public List<Player> findRemainingBatmenPlayers(Club clubId,Integer playerType,Integer matchTypeId);
   public List<Player> findRemainingBallerPlayers(Club club,Integer playerType,Integer matchTypeId);
}