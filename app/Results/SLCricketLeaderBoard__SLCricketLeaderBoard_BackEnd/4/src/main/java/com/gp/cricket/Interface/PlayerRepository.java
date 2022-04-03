package com.gp.cricket.Interface;
public interface PlayerRepository {

   public Player findPlayerById(Integer playerId);
   public Object existsById(Object Object);
}