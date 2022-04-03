package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.BatmanScoreRepository;
public class BatmanScoreRepositoryImpl implements BatmanScoreRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public BatmanScore getRecordByPlayerIdMatchType(Integer playerId,String matchType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRecordByPlayerIdMatchType"))
    .queryParam("playerId",playerId)
    .queryParam("matchType",matchType)
;  BatmanScore aux = restTemplate.getForObject(builder.toUriString(), BatmanScore.class);

 return aux;
}


public Object save(Object Object){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/save"))
    .queryParam("Object",Object)
;  Object aux = restTemplate.getForObject(builder.toUriString(), Object.class);

 return aux;
}


}