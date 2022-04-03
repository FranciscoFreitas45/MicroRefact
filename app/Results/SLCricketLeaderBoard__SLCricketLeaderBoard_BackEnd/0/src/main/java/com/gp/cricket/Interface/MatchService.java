package com.gp.cricket.Interface;
public interface MatchService {

   public List<Match> findMatchesByTournamentIdForCalender(Integer tournamentId);
}