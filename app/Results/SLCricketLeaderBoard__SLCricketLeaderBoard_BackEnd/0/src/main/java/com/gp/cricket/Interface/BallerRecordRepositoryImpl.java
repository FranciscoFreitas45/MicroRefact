package com.gp.cricket.Interface;
 import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import com.gp.cricket.Interface.BallerRecordRepository;
public class BallerRecordRepositoryImpl implements BallerRecordRepository{

@Autowired
 private RestTemplate restTemplate;

  String url = "http://3";


public BallerRecord findByPlayerIdANDMatchId(Integer playerId,Match matchId){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByPlayerIdANDMatchId"))
    .queryParam("playerId",playerId)
    .queryParam("matchId",matchId)
;  BallerRecord aux = restTemplate.getForObject(builder.toUriString(), BallerRecord.class);

 return aux;
}


public List<BallerRecord> findByPlayerIdANDMatchType(Integer playerId,Integer matchType){
  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/findByPlayerIdANDMatchType"))
    .queryParam("playerId",playerId)
    .queryParam("matchType",matchType)
;  List<BallerRecord> aux = restTemplate.getForObject(builder.toUriString(), List<BallerRecord>.class);

 return aux;
}


}