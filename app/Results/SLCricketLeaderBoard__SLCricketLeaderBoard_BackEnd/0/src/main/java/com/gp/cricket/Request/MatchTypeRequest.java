package com.gp.cricket.Request;
import com.gp.cricket.DTO.MatchType;
public interface MatchTypeRequest {

   public void setMatchTypeId(MatchType matchTypeId,Integer matchTypeIdv2);
   public MatchType getMatchTypeId(Integer matchTypeIdv2);
}