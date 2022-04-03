package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.BallerScoreService;
public class BallerScoreServiceImpl implements BallerScoreService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public BallerScore getRecordByPlayerIdMatchType(Integer playerId,String matchTypeString){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRecordByPlayerIdMatchType"))
    .queryParam("playerId",playerId)
    .queryParam("matchTypeString",matchTypeString)
;  BallerScore aux = restTemplate.getForObject(builder.toUriString(), BallerScore.class);

 return aux;
}


public BallerScore saveRecord(BallerScore ballerScoreRecord){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveRecord"))
    .queryParam("ballerScoreRecord",ballerScoreRecord)
;  BallerScore aux = restTemplate.getForObject(builder.toUriString(), BallerScore.class);

 return aux;
}


}