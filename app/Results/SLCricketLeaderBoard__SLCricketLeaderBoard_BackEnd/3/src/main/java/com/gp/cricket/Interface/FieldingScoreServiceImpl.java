package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.FieldingScoreService;
public class FieldingScoreServiceImpl implements FieldingScoreService{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://0";


public FieldingScore getRecordByPlayerIdMatchType(Integer playerId,String matchTypeString){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/getRecordByPlayerIdMatchType"))
    .queryParam("playerId",playerId)
    .queryParam("matchTypeString",matchTypeString)
;  FieldingScore aux = restTemplate.getForObject(builder.toUriString(), FieldingScore.class);

 return aux;
}


public FieldingScore saveFieldingRecord(FieldingScore record){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/saveFieldingRecord"))
    .queryParam("record",record)
;  FieldingScore aux = restTemplate.getForObject(builder.toUriString(), FieldingScore.class);

 return aux;
}


}