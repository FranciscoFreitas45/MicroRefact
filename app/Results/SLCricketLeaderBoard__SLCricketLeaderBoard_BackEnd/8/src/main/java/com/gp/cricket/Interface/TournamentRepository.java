package com.gp.cricket.Interface;
public interface TournamentRepository {

   public List<Tournament> findOnGoingTournament(Date date);
   public List<Tournament> findUpcomingTournament(Date date);
   public List<Tournament> findPastTournament(Date date);
}