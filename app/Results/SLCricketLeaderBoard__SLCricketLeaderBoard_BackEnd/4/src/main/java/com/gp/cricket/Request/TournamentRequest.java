package com.gp.cricket.Request;
import com.gp.cricket.DTO.Tournament;
public interface TournamentRequest {

   public void setTournamentId(Tournament tournamentId,Integer tournamentIdv2);
   public Tournament getTournamentId(Integer tournamentIdv2);
}