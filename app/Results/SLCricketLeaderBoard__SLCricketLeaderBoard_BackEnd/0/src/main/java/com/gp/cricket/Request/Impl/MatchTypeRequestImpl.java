package com.gp.cricket.Request.Impl;
 import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.DTO.MatchType;
import com.gp.cricket.Request.MatchTypeRequest;
public class MatchTypeRequestImpl implements MatchTypeRequest{

 private RestTemplate restTemplate = new RestTemplate();;


public void setMatchTypeId(MatchType matchTypeId,Integer matchTypeIdv2){
 restTemplate.put("http://2/BallerScore/{id}/MatchType/setMatchTypeId",matchTypeId,matchTypeIdv2);
 return ;
}


public MatchType getMatchTypeId(Integer matchTypeIdv2){
 MatchType aux = restTemplate.getForObject("http://2/BallerScore/{id}/MatchType/getMatchTypeId",MatchType.class,matchTypeIdv2);
return aux;
}


}