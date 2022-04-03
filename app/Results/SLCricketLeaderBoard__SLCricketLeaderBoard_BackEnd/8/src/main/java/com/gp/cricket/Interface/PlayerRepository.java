package com.gp.cricket.Interface;
public interface PlayerRepository {

   public List<String> findPlayerByClubId(Club clubId);
}