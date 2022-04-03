package com.gp.cricket.Interface;
public interface MatchRepository {

   public List<Match> findBytournamentId(Integer tournamentId);
}