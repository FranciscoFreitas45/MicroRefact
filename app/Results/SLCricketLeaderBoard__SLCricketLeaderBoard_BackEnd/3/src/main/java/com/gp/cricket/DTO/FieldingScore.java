package com.gp.cricket.DTO;
 import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.gp.cricket.Request.PlayerRequest;
import com.gp.cricket.Request.Impl.PlayerRequestImpl;
import com.gp.cricket.DTO.Player;
import com.gp.cricket.Request.MatchTypeRequest;
import com.gp.cricket.Request.Impl.MatchTypeRequestImpl;
import com.gp.cricket.DTO.MatchType;
public class FieldingScore {

 private  Integer fieldingId;

 private  Integer numberOfCatch;

 private  Double points;

 private  Player playerId;

 private  MatchType matchTypeId;

 private Integer playerIdv2;

 private Integer matchTypeIdv2;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";

public FieldingScore() {
    super();
// TODO Auto-generated constructor stub
}public FieldingScore(Integer fieldingId, Integer numberOfCatch, Double fieldingPoints, Player playerId, MatchType matchTypeId) {
    super();
    this.fieldingId = fieldingId;
    this.numberOfCatch = numberOfCatch;
    this.points = fieldingPoints;
    this.playerId = playerId;
    this.matchTypeId = matchTypeId;
}
public Player getPlayerId(){
    return playerId;
}


public Integer getFieldingId(){
    return fieldingId;
}


public MatchType getMatchTypeId(){
    return matchTypeId;
}


public Double getFieldingPoints(){
    return points;
}


public Double getPoints(){
    return points;
}


public Integer getNumberOfCatch(){
    return numberOfCatch;
}


public void updateNumberOfCatch(Integer numberOfCatch){
    this.numberOfCatch = this.numberOfCatch + numberOfCatch;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ fieldingId).concat("/updateNumberOfCatch"))

.queryParam("numberOfCatch",numberOfCatch)
;
restTemplate.put(builder.toUriString(),null);
}


public void updateFieldingPoints(Double fieldingPoints2){
    this.points = this.points + fieldingPoints2;
// TODO Auto-generated method stub
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ fieldingId).concat("/updateFieldingPoints"))

.queryParam("fieldingPoints2",fieldingPoints2)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFieldingId(Integer fieldingId){
    this.fieldingId = fieldingId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ fieldingId).concat("/setFieldingId"))

.queryParam("fieldingId",fieldingId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPlayerId(Player playerId){
 playerrequest.setPlayerId(playerId,this.playerIdv2);
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ fieldingId).concat("/setPlayerId"))

.queryParam("playerId",playerId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMatchTypeId(MatchType matchTypeId){
 matchtyperequest.setMatchTypeId(matchTypeId,this.matchTypeIdv2);
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ fieldingId).concat("/setMatchTypeId"))

.queryParam("matchTypeId",matchTypeId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNumberOfCatch(Integer numberOfCatch){
    this.numberOfCatch = numberOfCatch;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ fieldingId).concat("/setNumberOfCatch"))

.queryParam("numberOfCatch",numberOfCatch)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFieldingPoints(Double fieldingPoints){
    this.points = fieldingPoints;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ fieldingId).concat("/setFieldingPoints"))

.queryParam("fieldingPoints",fieldingPoints)
;
restTemplate.put(builder.toUriString(),null);
}


}