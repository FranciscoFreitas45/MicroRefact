package com.gp.cricket.Interface;
public interface TournamentClubPlayerRepository {

   public List<Player> findPlayersForMatch(Integer tournamentClubId);
}