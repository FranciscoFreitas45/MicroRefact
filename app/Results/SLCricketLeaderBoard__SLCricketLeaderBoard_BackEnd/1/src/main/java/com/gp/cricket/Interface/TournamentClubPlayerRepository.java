package com.gp.cricket.Interface;
public interface TournamentClubPlayerRepository {

   public List<TournamentClubPlayer> findPlayerTournamentStatus(Player playerId);
}