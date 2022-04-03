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
public class BallerScore {

 private  Integer ballerScoredId;

 private  Integer mathcCount;

 private  Double overs;

 private  Integer wickets;

 private  Double averageSpeed;

 private  Integer wideBalls;

 private  Integer noBalls;

 private  Integer numberOfRunsAgainst;

 private  Integer hatricks;

 private  Player playerId;

 private  MatchType matchTypeId;

 private  Double points;

 private Integer playerIdv2;

 private Integer matchTypeIdv2;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";

public BallerScore() {
    super();
// TODO Auto-generated constructor stub
}public BallerScore(Integer ballerScoredId, Integer mathcCount, Double overs, Integer wickets, Double averageSpeed, Integer wideBalls, Integer noBalls, Integer numberOfRunsAgainst, Integer hatricks, Player playerId, MatchType matchTypeId, Double points) {
    super();
    this.ballerScoredId = ballerScoredId;
    this.mathcCount = mathcCount;
    this.overs = overs;
    this.wickets = wickets;
    this.averageSpeed = averageSpeed;
    this.wideBalls = wideBalls;
    this.noBalls = noBalls;
    this.numberOfRunsAgainst = numberOfRunsAgainst;
    this.hatricks = hatricks;
    this.playerId = playerId;
    this.matchTypeId = matchTypeId;
    this.points = points;
}
public Player getPlayerId(){
    return playerId;
}


public Integer getWickets(){
    return wickets;
}


public Double getAverageSpeed(){
    return averageSpeed;
}


public Integer getHatricks(){
    return hatricks;
}


public Double getOvers(){
    return overs;
}


public Double getPoints(){
    return points;
}


public Integer getBallerScoredId(){
    return ballerScoredId;
}


public Integer getMatchCount(){
    return mathcCount;
}


public Integer getNoBalls(){
    return noBalls;
}


public Integer getNumberOfRunsAgainst(){
    return numberOfRunsAgainst;
}


public MatchType getMatchTypeId(){
    return matchTypeId;
}


public Integer getWideBalls(){
    return wideBalls;
}


public void updateMatchCount(Integer mathcCount){
    this.mathcCount = this.mathcCount + mathcCount;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/updateMatchCount"))

.queryParam("mathcCount",mathcCount)
;
restTemplate.put(builder.toUriString(),null);
}


public void setAverageSpeed(Double averageSpeed){
    this.averageSpeed = averageSpeed;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/setAverageSpeed"))

.queryParam("averageSpeed",averageSpeed)
;
restTemplate.put(builder.toUriString(),null);
}


public void updateOvers(Double overs){
    this.overs = +this.overs + overs;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/updateOvers"))

.queryParam("overs",overs)
;
restTemplate.put(builder.toUriString(),null);
}


public void updateWickets(Integer wickets){
    this.wickets = this.wickets + wickets;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/updateWickets"))

.queryParam("wickets",wickets)
;
restTemplate.put(builder.toUriString(),null);
}


public void updateWideBalls(Integer wideBalls){
    this.wideBalls = this.wideBalls + wideBalls;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/updateWideBalls"))

.queryParam("wideBalls",wideBalls)
;
restTemplate.put(builder.toUriString(),null);
}


public void updateNoBalls(Integer noBalls){
    this.noBalls = this.noBalls + noBalls;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/updateNoBalls"))

.queryParam("noBalls",noBalls)
;
restTemplate.put(builder.toUriString(),null);
}


public void updateNumberOfRunsAgainst(Integer numberOfRunsAgainst){
    this.numberOfRunsAgainst = this.numberOfRunsAgainst + numberOfRunsAgainst;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/updateNumberOfRunsAgainst"))

.queryParam("numberOfRunsAgainst",numberOfRunsAgainst)
;
restTemplate.put(builder.toUriString(),null);
}


public void updateHatricks(Integer hatricks){
    this.hatricks = this.hatricks + hatricks;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/updateHatricks"))

.queryParam("hatricks",hatricks)
;
restTemplate.put(builder.toUriString(),null);
}


public void updatePoints(Double points){
    this.points = this.points + points;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/updatePoints"))

.queryParam("points",points)
;
restTemplate.put(builder.toUriString(),null);
}


public void setBallerScoredId(Integer ballerScoredId){
    this.ballerScoredId = ballerScoredId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/setBallerScoredId"))

.queryParam("ballerScoredId",ballerScoredId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPlayerId(Player playerId){
 playerrequest.setPlayerId(playerId,this.playerIdv2);
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/setPlayerId"))

.queryParam("playerId",playerId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMatchTypeId(MatchType matchTypeId){
 matchtyperequest.setMatchTypeId(matchTypeId,this.matchTypeIdv2);
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/setMatchTypeId"))

.queryParam("matchTypeId",matchTypeId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMatchCount(Integer mathcCount){
    this.mathcCount = mathcCount;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/setMatchCount"))

.queryParam("mathcCount",mathcCount)
;
restTemplate.put(builder.toUriString(),null);
}


public void setOvers(Double overs){
    this.overs = overs;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/setOvers"))

.queryParam("overs",overs)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWickets(Integer wickets){
    this.wickets = wickets;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/setWickets"))

.queryParam("wickets",wickets)
;
restTemplate.put(builder.toUriString(),null);
}


public void setWideBalls(Integer wideBalls){
    this.wideBalls = wideBalls;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/setWideBalls"))

.queryParam("wideBalls",wideBalls)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNoBalls(Integer noBalls){
    this.noBalls = noBalls;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/setNoBalls"))

.queryParam("noBalls",noBalls)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNumberOfRunsAgainst(Integer numberOfRunsAgainst){
    this.numberOfRunsAgainst = numberOfRunsAgainst;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/setNumberOfRunsAgainst"))

.queryParam("numberOfRunsAgainst",numberOfRunsAgainst)
;
restTemplate.put(builder.toUriString(),null);
}


public void setHatricks(Integer hatricks){
    this.hatricks = hatricks;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/setHatricks"))

.queryParam("hatricks",hatricks)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPoints(Double points){
    this.points = points;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ ballerScoredId).concat("/setPoints"))

.queryParam("points",points)
;
restTemplate.put(builder.toUriString(),null);
}


}