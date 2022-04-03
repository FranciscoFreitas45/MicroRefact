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
public class BatmanScore {

 private  Integer batmanScoreId;

 private  Integer matchCount;

 private  Integer notOut;

 private  Integer facedBalls;

 private  Integer runs;

 private  Integer four;

 private  Integer six;

 private  Player playerId;

 private  MatchType matchTypeId;

 private  Double points;

 private  Integer halfCenturies;

 private  Integer centuries;

 private  Integer doubleCenturies;

 private  Integer tripleCenturies;

 private  Integer fourbleCenturies;

 private  Integer fivebleCenturies;

 private  Double strikeRate;

 private Integer playerIdv2;

 private Integer matchTypeIdv2;

 private RestTemplate restTemplate = new RestTemplate();

  String url = "http://0";

public BatmanScore() {
    super();
// TODO Auto-generated constructor stub
}public BatmanScore(Integer batmanScoreId, Integer matchCount, Integer notOut, Integer facedBalls, Integer runs, Integer four, Integer six, Player playerId, MatchType matchTypeId, Double points, Integer halfCenturies, Integer centuries, Integer doubleCenturies, Integer tripleCenturies, Integer fourbleCenturies, Integer fivebleCenturies, Double strikeRate) {
    super();
    this.batmanScoreId = batmanScoreId;
    this.matchCount = matchCount;
    this.notOut = notOut;
    this.facedBalls = facedBalls;
    this.runs = runs;
    this.four = four;
    this.six = six;
    this.playerId = playerId;
    this.matchTypeId = matchTypeId;
    this.points = points;
    this.halfCenturies = halfCenturies;
    this.centuries = centuries;
    this.doubleCenturies = doubleCenturies;
    this.tripleCenturies = tripleCenturies;
    this.fourbleCenturies = fourbleCenturies;
    this.fivebleCenturies = fivebleCenturies;
    this.strikeRate = strikeRate;
}
public Player getPlayerId(){
    return playerId;
}


public Double getPoints(){
    return points;
}


public Integer getBatmanScoreId(){
    return batmanScoreId;
}


public Integer getMatchCount(){
    return matchCount;
}


public Integer getFivebleCenturies(){
    return fivebleCenturies;
}


public Integer getFour(){
    return four;
}


public Integer getSix(){
    return six;
}


public Integer getHalfCenturies(){
    return halfCenturies;
}


public Double getStrikeRate(){
    return strikeRate;
}


public Integer getFacedBalls(){
    return facedBalls;
}


public Integer getNotOut(){
    return notOut;
}


public Integer getDoubleCenturies(){
    return doubleCenturies;
}


public Integer getFourbleCenturies(){
    return fourbleCenturies;
}


public MatchType getMatchTypeId(){
    return matchTypeId;
}


public Integer getRuns(){
    return runs;
}


public Integer getCenturies(){
    return centuries;
}


public Integer getTripleCenturies(){
    return tripleCenturies;
}


public void updateMatchCount(Integer matchCount){
    this.matchCount = this.matchCount + matchCount;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/updateMatchCount"))

.queryParam("matchCount",matchCount)
;
restTemplate.put(builder.toUriString(),null);
}


public void setStrikeRate(Double strikeRate){
    this.strikeRate = strikeRate;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/setStrikeRate"))

.queryParam("strikeRate",strikeRate)
;
restTemplate.put(builder.toUriString(),null);
}


public void updateFacedBalls(Integer facedBalls){
    this.facedBalls = this.facedBalls + facedBalls;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/updateFacedBalls"))

.queryParam("facedBalls",facedBalls)
;
restTemplate.put(builder.toUriString(),null);
}


public void updateFour(Integer four){
    this.four = this.four + four;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/updateFour"))

.queryParam("four",four)
;
restTemplate.put(builder.toUriString(),null);
}


public void updateSix(Integer six){
    this.six = this.six + six;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/updateSix"))

.queryParam("six",six)
;
restTemplate.put(builder.toUriString(),null);
}


public void updateRuns(Integer runs){
    this.runs = this.runs + runs;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/updateRuns"))

.queryParam("runs",runs)
;
restTemplate.put(builder.toUriString(),null);
}


public void updatePoints(Double points){
    this.points = this.points + points;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/updatePoints"))

.queryParam("points",points)
;
restTemplate.put(builder.toUriString(),null);
}


public void updateHalfCenturies(Integer halfCenturies){
    this.halfCenturies = this.halfCenturies + halfCenturies;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/updateHalfCenturies"))

.queryParam("halfCenturies",halfCenturies)
;
restTemplate.put(builder.toUriString(),null);
}


public void updateCenturies(Integer centuries){
    this.centuries = this.centuries + centuries;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/updateCenturies"))

.queryParam("centuries",centuries)
;
restTemplate.put(builder.toUriString(),null);
}


public void updateDoubleCenturies(Integer doubleCenturies){
    this.doubleCenturies = this.doubleCenturies + doubleCenturies;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/updateDoubleCenturies"))

.queryParam("doubleCenturies",doubleCenturies)
;
restTemplate.put(builder.toUriString(),null);
}


public void updateTripleCenturies(Integer tripleCenturies){
    this.tripleCenturies = this.tripleCenturies + tripleCenturies;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/updateTripleCenturies"))

.queryParam("tripleCenturies",tripleCenturies)
;
restTemplate.put(builder.toUriString(),null);
}


public void updateFourbleCenturies(Integer fourbleCenturies){
    this.fourbleCenturies = this.fourbleCenturies + fourbleCenturies;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/updateFourbleCenturies"))

.queryParam("fourbleCenturies",fourbleCenturies)
;
restTemplate.put(builder.toUriString(),null);
}


public void updateFivebleCenturies(Integer fivebleCenturies){
    this.fivebleCenturies = this.fivebleCenturies + fivebleCenturies;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/updateFivebleCenturies"))

.queryParam("fivebleCenturies",fivebleCenturies)
;
restTemplate.put(builder.toUriString(),null);
}


public void updateNotOut(Integer notOut){
    this.notOut = this.notOut + notOut;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/updateNotOut"))

.queryParam("notOut",notOut)
;
restTemplate.put(builder.toUriString(),null);
}


public void setBatmanScoreId(Integer batmanScoreId){
    this.batmanScoreId = batmanScoreId;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/setBatmanScoreId"))

.queryParam("batmanScoreId",batmanScoreId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPlayerId(Player playerId){
 playerrequest.setPlayerId(playerId,this.playerIdv2);
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/setPlayerId"))

.queryParam("playerId",playerId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMatchTypeId(MatchType matchTypeId){
 matchtyperequest.setMatchTypeId(matchTypeId,this.matchTypeIdv2);
 


  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/setMatchTypeId"))

.queryParam("matchTypeId",matchTypeId)
;
restTemplate.put(builder.toUriString(),null);
}


public void setMatchCount(Integer matchCount){
    this.matchCount = matchCount;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/setMatchCount"))

.queryParam("matchCount",matchCount)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFacedBalls(Integer facedBalls){
    this.facedBalls = facedBalls;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/setFacedBalls"))

.queryParam("facedBalls",facedBalls)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFour(Integer four){
    this.four = four;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/setFour"))

.queryParam("four",four)
;
restTemplate.put(builder.toUriString(),null);
}


public void setSix(Integer six){
    this.six = six;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/setSix"))

.queryParam("six",six)
;
restTemplate.put(builder.toUriString(),null);
}


public void setRuns(Integer runs){
    this.runs = runs;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/setRuns"))

.queryParam("runs",runs)
;
restTemplate.put(builder.toUriString(),null);
}


public void setPoints(Double points){
    this.points = points;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/setPoints"))

.queryParam("points",points)
;
restTemplate.put(builder.toUriString(),null);
}


public void setHalfCenturies(Integer halfCenturies){
    this.halfCenturies = halfCenturies;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/setHalfCenturies"))

.queryParam("halfCenturies",halfCenturies)
;
restTemplate.put(builder.toUriString(),null);
}


public void setCenturies(Integer centuries){
    this.centuries = centuries;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/setCenturies"))

.queryParam("centuries",centuries)
;
restTemplate.put(builder.toUriString(),null);
}


public void setDoubleCenturies(Integer doubleCenturies){
    this.doubleCenturies = doubleCenturies;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/setDoubleCenturies"))

.queryParam("doubleCenturies",doubleCenturies)
;
restTemplate.put(builder.toUriString(),null);
}


public void setTripleCenturies(Integer tripleCenturies){
    this.tripleCenturies = tripleCenturies;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/setTripleCenturies"))

.queryParam("tripleCenturies",tripleCenturies)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFourbleCenturies(Integer fourbleCenturies){
    this.fourbleCenturies = fourbleCenturies;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/setFourbleCenturies"))

.queryParam("fourbleCenturies",fourbleCenturies)
;
restTemplate.put(builder.toUriString(),null);
}


public void setFivebleCenturies(Integer fivebleCenturies){
    this.fivebleCenturies = fivebleCenturies;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/setFivebleCenturies"))

.queryParam("fivebleCenturies",fivebleCenturies)
;
restTemplate.put(builder.toUriString(),null);
}


public void setNotOut(Integer notOut){
    this.notOut = notOut;
 

  UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(url.concat("/"+ batmanScoreId).concat("/setNotOut"))

.queryParam("notOut",notOut)
;
restTemplate.put(builder.toUriString(),null);
}


}