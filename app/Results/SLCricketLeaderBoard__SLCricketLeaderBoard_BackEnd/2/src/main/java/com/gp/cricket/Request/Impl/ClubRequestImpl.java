package com.gp.cricket.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.DTO.Club;
import com.gp.cricket.Request.ClubRequest;
public class ClubRequestImpl implements ClubRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public Club getClubId(Integer clubIdv2){
 Club aux = restTemplate.getForObject("http://8/ClubRanking/{id}/Club/getClubId",Club.class,clubIdv2);
return aux;
}


public void setClubId(Club clubId,Integer clubIdv2){
 restTemplate.put("http://8/ClubRanking/{id}/Club/setClubId",clubId,clubIdv2);
 return ;
}


}