package com.gp.cricket.Request;
import com.gp.cricket.DTO.Match;
public interface MatchRequest {

   public Match getMatchId(Integer matchIdv2);
   public void setMatchId(Match matchId,Integer matchIdv2);
}