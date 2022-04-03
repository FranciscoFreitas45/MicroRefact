package com.gp.cricket.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.DTO.Stadium;
import com.gp.cricket.Request.StadiumRequest;
public class StadiumRequestImpl implements StadiumRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setStadiumId(Stadium stadiumId,Integer stadiumIdv2){
 restTemplate.put("http://12/Match/{id}/Stadium/setStadiumId",stadiumId,stadiumIdv2);
 return ;
}


public Stadium getStadiumId(Integer stadiumIdv2){
 Stadium aux = restTemplate.getForObject("http://12/Match/{id}/Stadium/getStadiumId",Stadium.class,stadiumIdv2);
return aux;
}


}