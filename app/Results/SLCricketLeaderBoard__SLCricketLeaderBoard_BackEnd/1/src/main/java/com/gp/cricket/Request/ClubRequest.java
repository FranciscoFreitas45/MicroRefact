package com.gp.cricket.Request;
import com.gp.cricket.DTO.Club;
public interface ClubRequest {

   public Club getClubId(Integer clubIdv2);
   public void setClubId(Club clubId,Integer clubIdv2);
}