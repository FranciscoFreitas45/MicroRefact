package com.gp.cricket.Interface;
public interface TournamentClubRepository {

   public Integer findIdByTournamentAndClub(Integer tournamentId,Integer clubId);
   public TournamentClub findByClubIdAndTournamentId(Club clubId,Tournament tournamentId);
}